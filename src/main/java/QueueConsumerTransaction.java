import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;

public class QueueConsumerTransaction {

    public static void main(String[] args) throws NamingException, JMSException {

        var context = new InitialContext();
        ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(true, Session.SESSION_TRANSACTED);

        Destination queue = (Destination) context.lookup("filaFinanceiro");
        MessageConsumer consumer = session.createConsumer(queue);

        consumer.setMessageListener(message -> {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println(textMessage.getText());
                session.commit();
            } catch (JMSException e) {
                System.out.println(message);
            }
        });
        new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        context.close();

    }

}
