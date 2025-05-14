package br.lippi.flashcards.pi.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    
    private String host = "localhost";
    private String port = "3306";
    private String db = "flashcards";
    private String user = "root";
    private String password = "pliquio1";
    
    public Connection obterConexao() throws Exception {
        var s = String.format(
             "jdbc:mysql://%s:%s/%s?useSSL=false&serverTimezone=UTC",
            host, port, db    
        );
        Connection c = DriverManager.getConnection(s, user, password);
        return c;
    }
    
}
