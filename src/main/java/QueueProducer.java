import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class QueueProducer {

    public static void main(String[] args) throws NamingException, JMSException {

        var context = new InitialContext();
        ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination fila = (Destination) context.lookup("filaFinanceiro");

        MessageProducer producer = session.createProducer(fila);


        for (int i = 0; i < 1000; i++) {
            Message message = session.createTextMessage("MSG ID: " + i);
            producer.send(message);
        }

        session.close();
        connection.close();
        context.close();

    }

}
