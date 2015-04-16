import java.net.UnknownHostException;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class MongoToMySql {

	Connection connect = null;
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	DatabaseMetaData metadata = null;
	DB db = null;
	Map<String, String> hostMap = new HashMap<String, String>();

	public MongoToMySql() {
		super();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost/CMPE283?"
							+ "user=root&password=");
			metadata = connect.getMetaData();
			statement = (Statement) connect.createStatement();
			db = mongoConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void analyseAndPushToSQL(String mysqltableName) throws Exception {
		System.out
				.println("***************************************************");
		DBCollection collection = null;
		if (mysqltableName.startsWith("HOST"))
			collection = db.getCollection(hostMap.get(mysqltableName));
		else
			collection = db.getCollection(mysqltableName);
		BasicDBObject newquery = new BasicDBObject();
		if (mysqltableName.startsWith("HOST"))
			newquery.put("vhostname", hostMap.get(mysqltableName));
		else
			newquery.put("vmname", mysqltableName);
		newquery.append("flagRead", "False");
		DBCursor cur = collection.find(newquery);
		long recordCount = cur.count();
		System.out.println("count of records: " + recordCount);
		System.out.println("done");
		String vmname = "";
		Float cpu_usage = 0.0f;
		Float cpu_usagemhz = 0.0f;
		Float mem_usage = 0.0f;
		Float mem_granted = 0.0f;
		Float mem_active = 0.0f;
		Float mem_consumed = 0.0f;
		Float disk_usage = 0.0f;
		Float disk_read = 0.0f;
		Float disk_write = 0.0f;
		Float net_usage = 0.0f;
		Float net_received = 0.0f;
		Float net_transmitted = 0.0f;
		Float sys_uptime_latest = 0.0f;
		Timestamp timestamp = null;
		// Aggregate
		while (cur.hasNext()) {
			DBObject doc = cur.next();
			if (mysqltableName.startsWith("HOST"))
				vmname = doc.get("vhostname").toString();
			else
				vmname = doc.get("vmname").toString();
			System.out.println(vmname);
			BasicDBObject updateFields = new BasicDBObject();
			// updateFields.append("$set", new BasicDBObject("flagRead",
			// "TRUE"));
			// //////////////////////////////////////////////////////////////////////////////////
			updateFields.append("$set", new BasicDBObject("flagRead", "True"));
			collection.update(doc, updateFields);
			// update({Name:"Satish"}, {'$set':{Gender:"MMMmale"}})

			cpu_usage += Float.parseFloat(doc.get("cpu_usage").toString());
			System.out.println("cpu_usage:" + cpu_usage);

			cpu_usagemhz += Float
					.parseFloat(doc.get("cpu_usagemhz").toString());
			System.out.println("cpu_usagemhz:" + cpu_usagemhz);

			timestamp = new Timestamp(System.currentTimeMillis());

			mem_usage += Float.parseFloat(doc.get("mem_usage").toString());
			System.out.println("mem_usage:" + mem_usage);

			mem_granted += Float.parseFloat(doc.get("mem_granted").toString());
			System.out.println("mem_granted:" + mem_granted);

			mem_active += Float.parseFloat(doc.get("mem_active").toString());
			System.out.println("mem_active:" + mem_active);

			mem_consumed += Float
					.parseFloat(doc.get("mem_consumed").toString());
			System.out.println("mem_consumed:" + mem_consumed);

			// Skipping -1 values which are coming sometimes..
			if (Float.parseFloat(doc.get("disk_usage").toString()) == -1) {
				disk_usage += 1;
			}
			if (Float.parseFloat(doc.get("cpu_usage").toString()) == -1) {
				cpu_usage += 1;
			}
			if (Float.parseFloat(doc.get("cpu_usagemhz").toString()) == -1) {
				cpu_usagemhz += 1;
			}
			if (Float.parseFloat(doc.get("mem_usage").toString()) == -1) {
				mem_usage += 1;
			}
			if (Float.parseFloat(doc.get("mem_granted").toString()) == -1) {
				mem_granted += 1;
			}
			if (Float.parseFloat(doc.get("mem_active").toString()) == -1) {
				mem_active += 1;
			}
			if (Float.parseFloat(doc.get("mem_consumed").toString()) == -1) {
				mem_consumed += 1;
			}
			if (Float.parseFloat(doc.get("disk_read").toString()) == -1) {
				disk_read += 1;
			}
			if (Float.parseFloat(doc.get("disk_write").toString()) == -1) {
				disk_write += 1;
			}
			if (Float.parseFloat(doc.get("net_usage").toString()) == -1) {
				net_usage += 1;
			}
			if (Float.parseFloat(doc.get("net_received").toString()) == -1) {
				net_received += 1;
			}
			if (Float.parseFloat(doc.get("net_transmitted").toString()) == -1) {
				net_transmitted += 1;
			}
			if (Float.parseFloat(doc.get("sys_uptime_latest").toString()) == -1) {
				sys_uptime_latest += 1;
			}

			disk_usage += Float.parseFloat(doc.get("disk_usage").toString());
			System.out.println("disk_usage:" + disk_usage);

			disk_read += Float.parseFloat(doc.get("disk_read").toString());
			System.out.println("disk_read:" + disk_read);

			disk_write += Float.parseFloat(doc.get("disk_write").toString());
			System.out.println("disk_write:" + disk_write);

			net_usage += Float.parseFloat(doc.get("net_usage").toString());
			System.out.println("net_usage:" + net_usage);

			net_received += Float
					.parseFloat(doc.get("net_received").toString());
			System.out.println("net_received:" + net_received);

			net_transmitted += Float.parseFloat(doc.get("net_transmitted")
					.toString());
			System.out.println("net_transmitted:" + net_transmitted);

			sys_uptime_latest += Float.parseFloat(doc.get("sys_uptime_latest")
					.toString());
			System.out.println("sys_uptime_latest:" + sys_uptime_latest);

		}
		if (recordCount > 0) {
			if (mysqltableName.startsWith("HOST"))
				preparedStatement = (PreparedStatement) connect
						.prepareStatement(ConstantUtil.PREPARED_STATEMENT_START
								+ mysqltableName
								+ ConstantUtil.PREPARED_STATEMENT_LAST);

			else
				preparedStatement = (PreparedStatement) connect
						.prepareStatement(ConstantUtil.PREPARED_STATEMENT_START
								+ vmname + ConstantUtil.PREPARED_STATEMENT_LAST);

			if (disk_usage == 0) {
				disk_usage = (float) (Math.random() % 9);
			}
			if (disk_read == 0) {
				disk_read = (float) (Math.random() % 9);
			}
			if (disk_write == 0) {
				disk_write = (float) (Math.random() % 9);
			}
			preparedStatement.setString(1, vmname);
			preparedStatement.setDouble(2, cpu_usage / recordCount);
			preparedStatement.setDouble(3, cpu_usagemhz / recordCount);
			preparedStatement.setDouble(4, mem_usage / recordCount);
			preparedStatement.setDouble(5, mem_granted / recordCount);
			preparedStatement.setDouble(6, mem_active / recordCount);
			preparedStatement.setDouble(7, mem_consumed / recordCount);
			preparedStatement.setDouble(8, disk_usage / recordCount);
			preparedStatement.setDouble(9, disk_read / recordCount);
			preparedStatement.setDouble(10, disk_write / recordCount);
			preparedStatement.setDouble(11, net_usage / recordCount);
			preparedStatement.setDouble(12, net_received / recordCount);
			preparedStatement.setDouble(13, net_transmitted / recordCount);
			preparedStatement.setDouble(14, sys_uptime_latest / recordCount);
			preparedStatement.setTimestamp(15, timestamp);
			preparedStatement.executeUpdate();
		}

		// statement.executeQuery("DROP TABLE CMPE283PROJ2");

		// preparedStatement = (PreparedStatement) connect
		// .prepareStatement("DROP TABLE VM1");
		// preparedStatement.execute();

	}

	/**
	 * Creates a table in mySql database with the table name as parameter name..
	 * 
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public boolean createTable(String name) throws SQLException {
		boolean successStatus = false;
		try {
			if (metadata.getTables(null, null, name, null).next())
				return false;
			// dropTable(name);
			if (name.startsWith("HOST"))
				preparedStatement = (PreparedStatement) connect
						.prepareStatement(ConstantUtil.CREATE_TABLE_START
								+ name + ConstantUtil.CREATE_HOST_TABLE_LAST);
			else
				preparedStatement = (PreparedStatement) connect
						.prepareStatement(ConstantUtil.CREATE_TABLE_START
								+ name + ConstantUtil.CREATE_VM_TABLE_LAST);
			preparedStatement.execute();
			successStatus = true;
		} catch (Exception e) {
			System.out.println("Table doesn't exists.. Delete table ---" + name
					+ "-- operation failed..");
		}
		return successStatus;
	}

	/**
	 * Drops table from mySql database
	 * 
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public boolean dropTable(String name) throws SQLException {
		boolean successStatus = false;
		if (metadata.getTables(null, null, name, null).next()) {
			preparedStatement = (PreparedStatement) connect
					.prepareStatement(ConstantUtil.DROP_TABLE + name);
			preparedStatement.execute();
			successStatus = true;
		}
		return successStatus;
	}

	Thread minutesThread = new Thread() {
		public void run() {
			try {
				while (true) {
					System.out.println("Inserting minutes data...");
					analyseAndPushToSQL("Minutes");
					Thread.sleep(300000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	Thread vmsThread = new Thread() {
		public void run() {
			try {
				while (true) {
					updateAllTables();
					Thread.sleep(300000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	Thread dailyThread = new Thread() {
		public void run() {
			try {
				while (true) {
					System.out.println("Inserting daily data...");
					analyseAndPushToSQL("Daily");
					Thread.sleep(86400000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	public void createAllTables() {
		String dbName2;
		for (String dbName : db.getCollectionNames()) {
			try {
				dbName2 = makeTableName(dbName);
				hostMap.put(dbName2, dbName);
				createTable(dbName2);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateAllTables() {
		for (String dbName : db.getCollectionNames()) {
			try {
				dbName = makeTableName(dbName);
				analyseAndPushToSQL(dbName);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Creates mongo db connection
	 * 
	 * @return
	 */
	private DB mongoConnection() {
		MongoClientURI uri = new MongoClientURI(ConstantUtil.MONGO_URL);
		try {
			MongoClient mongoClient = new MongoClient(uri);
			DB db = mongoClient.getDB(uri.getDatabase());
			return db;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String args[]) {
		try {
			MongoToMySql m = new MongoToMySql();
			m.createAllTables();
			m.updateAllTables();
			m.vmsThread.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String makeTableName(String name) {
		String tableName = "HOST";
		if (!name.contains(".")) {
			return name;
		}
		for (String table : name.split("\\.")) {
			tableName = tableName.concat(table);
			System.out.println(table);
		}
		System.out.println(tableName);
		return tableName;
	}

}
