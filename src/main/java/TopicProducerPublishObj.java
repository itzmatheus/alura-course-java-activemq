import br.com.caelum.modelo.PedidoFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TopicProducerPublishObj {

    public static void main(String[] args) throws NamingException, JMSException {

        var context = new InitialContext();
        ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination topic = (Destination) context.lookup("topicoLoja");

        MessageProducer producer = session.createProducer(topic);

        var order = new PedidoFactory().geraPedidoComValores();

        Message message = session.createObjectMessage(order);
        producer.send(message);


        session.close();
        connection.close();
        context.close();

    }

}
