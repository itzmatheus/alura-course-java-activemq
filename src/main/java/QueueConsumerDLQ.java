import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;

public class QueueConsumerDLQ {

    public static void main(String[] args) throws NamingException, JMSException {

        var context = new InitialContext();
        ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination queue = (Destination) context.lookup("DLQ");
        MessageConsumer consumer = session.createConsumer(queue);

        consumer.setMessageListener(System.out::println);
        new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        context.close();

    }

}
