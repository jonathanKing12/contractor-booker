package setting;



public interface AccessSetting {

	void setLocation(String location) throws SettingException;

	String getLocation() throws SettingException;

}
