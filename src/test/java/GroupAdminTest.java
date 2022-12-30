import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.Member;
import observer.Sender;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupAdminTest {
    public static final Logger logger = LoggerFactory.getLogger(GroupAdminTest.class);

    @Test
    public void build() {
        GroupAdmin groupAdmin1 = new GroupAdmin();

        groupAdmin1.register(new ConcreteMember("m11"));
        groupAdmin1.register(new ConcreteMember("m12", 10, "*"));
        groupAdmin1.register(new ConcreteMember("m13", 10, 20, "*"));

        assertEquals("", groupAdmin1.getUndoableStringBuilder().toString());

        GroupAdmin groupAdmin2 = new GroupAdmin("Group Admin 2");

        groupAdmin2.register(new ConcreteMember("m21"));
        groupAdmin2.register(new ConcreteMember("m22", 10, "*"));
        groupAdmin2.register(new ConcreteMember("m23", 10, 20, "*"));

        assertEquals("Group Admin 2", groupAdmin2.getUndoableStringBuilder().toString());

        GroupAdmin groupAdmin3 = new GroupAdmin(20);

        groupAdmin3.register(new ConcreteMember("m31"));
        groupAdmin3.register(new ConcreteMember("m32", 10, "*"));
        groupAdmin3.register(new ConcreteMember("m33", 10, 20, "*"));

        assertEquals("", groupAdmin3.getUndoableStringBuilder().toString());
    }

    @Test
    public void groupAdmin () {
        String s1 = "Alice had a little house and she feared the big bad wolf \n bob was safe inside the house tho cold and aloof";
        String s2 = "Bob";

        // init admin 1
        Sender admin1 = new GroupAdmin();

        Member member1 = new ConcreteMember("member1");
        Member member2 = new ConcreteMember("member2", 5, 140, "alice");
        Member member3 = new ConcreteMember("member3", 0, 7, "bob");

        admin1.register(member1);
        admin1.register(member2);
        admin1.register(member3);

        assertEquals("", admin1.toString());

        // actions admin 1
        admin1.append(s2);

        assertEquals("Bobalic", admin1.toString());

        admin1.undo();
        assertEquals("Bobalic", admin1.toString());

        admin1.delete(0,4);
        assertEquals("licalic", admin1.toString());

        admin1.unregister(member1);
        admin1.unregister(member2);
        admin1.unregister(member1);
        admin1.unregister(member3);

        // admin 2
        Sender admin2 = new GroupAdmin(s1);

        for (int i = 0; i < 10; i++) {
            admin2.insert(15,"but no");
        }

        for (int i = 0; i < 10; i++) {
            admin2.delete(15,21);
        }

        assertEquals(s1, admin2.toString());

        for (int i = 0; i < 20; i++) {
            admin2.undo();
        }
    }

    @Test
    public void memory () {
        String s1 = "Alice had a little house and she feared the big bad wolf \n bob was safe inside the house tho cold and aloof";
        String s2 = "Bob";
        Sender admin1 = new GroupAdmin(s2);
        Member member1 = new ConcreteMember("member1",5,140,"alice");
        Member member2 = new ConcreteMember("member2",0,7,"bob");
        logger.info(()->JvmUtilities.objectFootprint(admin1));
        admin1.register(member1);
        admin1.register(member2);
        assertEquals("bobalic", admin1.toString());
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
