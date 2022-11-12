import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Time;

public class QueueProducerWithPriority {

    public static void main(String[] args) throws NamingException, JMSException {

        var context = new InitialContext();
        ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination queue = (Destination) context.lookup("filaFinanceiro");

        MessageProducer producer = session.createProducer(queue);

        Message message = session.createTextMessage("MSG");
        var FIVE_SECONDS_IN_MILLI = 5000;
        var LOW_PRIORITY = 3;
        producer.send(message, DeliveryMode.NON_PERSISTENT, LOW_PRIORITY, FIVE_SECONDS_IN_MILLI);


        session.close();
        connection.close();
        context.close();

    }

}
