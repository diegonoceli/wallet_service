   import org.junit.jupiter.api.BeforeEach;
   import org.junit.jupiter.api.Test;
   import org.junit.jupiter.api.extension.ExtendWith;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
   import org.springframework.boot.test.context.SpringBootTest;
   import org.springframework.test.context.junit.jupiter.SpringExtension;
   import org.springframework.test.web.servlet.MockMvc;
   import org.testcontainers.containers.PostgreSQLContainer;
   import org.testcontainers.junit.jupiter.Container;
   import org.testcontainers.junit.jupiter.Testcontainers;

   import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
   import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
   import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

   @ExtendWith(SpringExtension.class)
   @SpringBootTest
   @AutoConfigureMockMvc
   @Testcontainers
   public class WalletIntegrationTest {

       @Container
       public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
           .withDatabaseName("testdb")
           .withUsername("test")
           .withPassword("test");

       @Autowired
       private MockMvc mockMvc;

       @BeforeEach
       public void setup() {
           // Configurar o DataSource com as informações do container postgres
           System.setProperty("spring.datasource.url", postgreSQLContainer.getJdbcUrl());
           System.setProperty("spring.datasource.username", postgreSQLContainer.getUsername());
           System.setProperty("spring.datasource.password", postgreSQLContainer.getPassword());
       }

       @Test
       public void testCreateWallet() throws Exception {
           mockMvc.perform(post("/wallets")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content("{\"name\": \"John\", \"surname\": \"Doe\", \"documentNumber\": \"1234567890\"}"))
                   .andExpect(status().isOk())
                   .andExpect(jsonPath("$.name").value("John"))
                   .andExpect(jsonPath("$.surname").value("Doe"));
       }

       // Adicione outros testes como o de depósito, saque e transferência aqui
   }