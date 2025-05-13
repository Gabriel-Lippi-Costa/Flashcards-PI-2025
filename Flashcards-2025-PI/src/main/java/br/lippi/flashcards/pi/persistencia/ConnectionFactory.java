package br.lippi.flashcards.pi.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    
    private String host = "";
    private String port = "";
    private String db = "";
    private String user = "";
    private String password = "";
    
    public Connection obterConexao() throws Exception {
        var s = String.format(
            "jdbc:mysql://%s:%s/%s",
            host, port, db    
        );
        Connection c = DriverManager.getConnection(s, user, password);
        return c;
    }
    
}
