import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.Member;
import observer.Sender;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility
    @Test
    public void test(){
        String s1 = "Alice had a little house and she feared the big bad wolf \n bob was safe inside the house tho cold and aloof";
        String s2 = "Bob";
        Sender admin1 = new GroupAdmin(s2);
        Member member1 = new ConcreteMember("member1",5,140,"alice");
        Member member2 = new ConcreteMember("member2",0,7,"bob");
        logger.info(()->JvmUtilities.objectFootprint(admin1));
        admin1.register(member1);
        admin1.register(member2);
        assertEquals("bobalic",admin1.toString());
        admin1.undo();
        assertEquals("bobalic",admin1.toString());
        admin1.delete(0,4);
        assertEquals(admin1.toString(),"licalice");


        logger.info(()->JvmUtilities.objectFootprint(admin1));

        logger.info(()->JvmUtilities.objectFootprint(member1,member2));

        logger.info(()->JvmUtilities.objectTotalSize(admin1));

        logger.info(() -> JvmUtilities.jvmInfo());
        admin1.unregister(member1);
        admin1.unregister(member2);
        logger.info(()->JvmUtilities.objectFootprint(admin1));
        admin1.unregister(member1);
        Sender admin2 = new GroupAdmin(s1);
        for (int i = 0; i < 10; i++) {
            admin2.insert(15,"but no");
        }
        for (int i = 0; i < 10; i++) {
            admin2.delete(15,21);
        }
        assertEquals(admin2.toString(),s1);
        logger.info(()->JvmUtilities.objectTotalSize(admin2));
        for (int i = 0; i < 20; i++) {
            admin2.undo();
        }
        logger.info(()->JvmUtilities.objectTotalSize(admin2));
        logger.info(() -> JvmUtilities.jvmInfo());


    }
}
