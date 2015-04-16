import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConstantUtil {

	public static final List<String> METRIC_LIST = new ArrayList<String>(
			Arrays.asList("cpu", "datastore", "disk", "mem", "net", "power",
					"sys"));
	// public static final List<String> METRIC_LIST = new ArrayList<String>(
	// Arrays.asList("cpu_usage", "datastore", "disk_usage", "mem_consumed",
	// "net", "power_power",
	// "sys_uptime"));

	public static String URL = "https://130.65.132.101/sdk";
	public static String ADMIN_USER_NAME = "administrator";
	// public static String ADMIN_USER_NAME = "root";
	public static String ADMIN_PASSWORD = "12!@qwQW";
	public static List<String> PARAMETER_LIST = new ArrayList<String>(
			Arrays.asList("cpu_usage", "cpu_usagemhz", "mem_usage",
					"mem_granted", "mem_active", "mem_consumed", "disk_usage",
					"disk_read", "disk_write", "net_usage", "net_received",
					"net_transmitted", "sys_uptime_latest"));

	public static List<String> PROJECT_PARAMETER_LIST_DRS1 = new ArrayList<String>(
			Arrays.asList("cpu_usagemhz"));

	public static List<String> PROJECT_PARAMETER_LIST_DRS2 = new ArrayList<String>(
			Arrays.asList("cpu_usage"));

	public static String PREPARED_STATEMENT_START = "insert into ";
	public static String PREPARED_STATEMENT_LAST = " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
	public static String CREATE_TABLE_START = "CREATE TABLE ";
	public static String CREATE_VM_TABLE_LAST = "( vmname VARCHAR(45) NOT NULL,  cpu_usage DOUBLE NULL,  cpu_usagemhz DOUBLE NULL,  mem_usage DOUBLE NULL,  mem_granted DOUBLE NULL,  mem_active DOUBLE NULL,  mem_consumed DOUBLE NULL,  disk_usage DOUBLE NULL,  disk_read DOUBLE NULL,  disk_write DOUBLE NULL,  net_usage DOUBLE NULL,  net_received DOUBLE NULL,  net_transmitted DOUBLE NULL,  sys_uptime_latest DOUBLE NULL,  timestamp TIMESTAMP NOT NULL,    PRIMARY KEY (Timestamp))";
	public static String CREATE_HOST_TABLE_LAST = "( vhostname VARCHAR(45) NOT NULL,  cpu_usage DOUBLE NULL,  cpu_usagemhz DOUBLE NULL,  mem_usage DOUBLE NULL,  mem_granted DOUBLE NULL,  mem_active DOUBLE NULL,  mem_consumed DOUBLE NULL,  disk_usage DOUBLE NULL,  disk_read DOUBLE NULL,  disk_write DOUBLE NULL,  net_usage DOUBLE NULL,  net_received DOUBLE NULL,  net_transmitted DOUBLE NULL,  sys_uptime_latest DOUBLE NULL,  timestamp TIMESTAMP NOT NULL,    PRIMARY KEY (Timestamp))";
	public static String DROP_TABLE = "DROP TABLE ";
	public static String VHost1 = "130.65.132.132";
	public static String VHost2 = "130.65.132.133";
	public static String NFS_Name = "NFSDS05";
	public static String GuestOS = "ubuntuGuest";
	public static String NEW_VM_NAME = "VM05_Ubuntu_NEW";
	public static String NEW_VM_ANNOTATION = "New VirtualMachine Annotation";
	public static String SSL_THUMBPRINT_HOST_173 = "02:05:54:83:FF:3E:4B:45:D1:32:7D:2F:77:74:46:96:31:CD:AD:49";
//			"7A:94:14:58:75:36:2B:FD:FC:6F:50:10:15:7F:CB:F1:0B:92:71:10";

	public static String MONGO_URL = "mongodb://cmpe283:cmpe283@ds051970.mongolab.com:51970/cmpe283";
	public static List<String> vmsList = new ArrayList<String>(Arrays.asList("T01-VM01","PlainVM","T01-VM02"));
}
