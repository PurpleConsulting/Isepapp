import org.purple.bean.User;
import org.purple.constant.Bdd;
import org.purple.model.*;

import java.sql.*;

public class Main {

	public static void main(String[] args) {
		Connection co = Bdd.getCo();
		DaoUsers u = new DaoUsers(co);
		System.out.print(u.find("833"));
	}

}
