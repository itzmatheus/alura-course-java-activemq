import br.com.caelum.modelo.Pedido;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;

public class TopicConsumerObj {

    public static void main(String[] args) throws NamingException, JMSException {

        var context = new InitialContext();
        ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = connectionFactory.createConnection();
        connection.setClientID("stock");

        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = (Topic) context.lookup("topicoLoja");
        MessageConsumer consumer = session.createDurableSubscriber(topic, "subscriber");

        consumer.setMessageListener(message -> {
            ObjectMessage object = (ObjectMessage) message;
            try {
                Pedido order = (Pedido) object.getObject();
                System.out.println(order);
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        });
        new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        context.close();

    }

}
