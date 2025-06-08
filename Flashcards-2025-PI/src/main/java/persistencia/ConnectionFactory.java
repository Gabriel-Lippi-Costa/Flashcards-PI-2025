package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    
    private String host = "mysql-22f5829e-flashcards-poliedro.j.aivencloud.com"; 
    private String port = "10642"; 
    private String db = "flashcards";  
    private String user = "avnadmin"; 
    private String password = "AVNS_oju_9j4RCPrdZ_tqLp4";

    public Connection obterConexao() throws Exception {
        String url = String.format(
            "jdbc:mysql://%s:%s/%s?sslMode=REQUIRED&serverTimezone=UTC&verifyServerCertificate=false",
            host, port, db
        );
        Connection c = DriverManager.getConnection(url, user, password);
        return c;
    }
}
