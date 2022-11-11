import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;

public class Consumer {

    public static void main(String[] args) throws NamingException, JMSException {

        var context = new InitialContext();
        ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination fila = (Destination) context.lookup("filaFinanceiro");
        MessageConsumer consumer = session.createConsumer(fila);

        Message message = consumer.receive();
        System.out.println("Receiving msg: " + message);
        new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        context.close();

    }

}
