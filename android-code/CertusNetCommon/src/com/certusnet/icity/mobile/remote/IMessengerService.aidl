package com.certusnet.icity.mobile.remote;  
import com.certusnet.icity.mobile.remote.UserInfo;
import com.certusnet.icity.mobile.remote.RegionInfo;
import com.certusnet.icity.mobile.remote.AdvInfo;
import com.certusnet.icity.mobile.remote.AppInfo;
import com.certusnet.icity.mobile.remote.Parameter;
import com.certusnet.icity.mobile.remote.CallBack;
import java.util.List;
import android.os.Bundle;
/**
 *提供第三方服务接口，由第三方调用。详细请看提供的具体方法
 * @author lig
 * @version 1.0
 * @since 1.0
 */
interface IMessengerService { 

	/**
	 * 用户是否登录,暂不支持
	 * 
	 * @param userName ;null 则直接判断当前是否有用户登录
	 * @return 用户已登录返回true，否则返回false
	 * @throws android.os.RemoteException
	 */
    boolean isLogin(String userName);
    
 	/**
	 * 获取当前登录用户信息
	 * 
	 * @return 用户已登录则返回当前用户信息，否则返回 null
	 * @throws android.os.RemoteException
	 */   
    UserInfo getCurrentUserInfo();

	/**
	 * 获取所有用户信息(受限)
	 * 
	 * @return 
	 * @throws android.os.RemoteException
	 */    
    List<UserInfo> getAllUserInfo();

	 /**
	 * 获取用户的密码（MD5）
	 * 
	 * @param userName
	 * @return
	 * @throws android.os.RemoteException
	 */ 
	String getPassword(String userName);

	/**
	 * 获取当前登录用户的区域信息
	 * 
	 * @return 没有登录用户则返回null
	 * @throws android.os.RemoteException
	 */
    RegionInfo getCurrentRegionInfo();
    
	/**
	 * 获取 用户的区域信息 （根据输入的用户名）
	 * 
	 * @param userName
	 * @return
	 * @throws android.os.RemoteException
	 */    
    RegionInfo getRegionInfoByUserName(String userName);
    
	/**
	 * 获取本地已存在的所有区域信息
	 * 
	 * @return
	 * @throws android.os.RemoteException
	 */    
    List<RegionInfo> getLocalRegionInfo();
    
   /**搜素区域信息，,暂不支持
	* 注：当local=false并API level>11时，请不要再UI线程中调用此接口，否则返回值为null；
	* 后台隐藏了{@link android.os.NetworkOnMainThreadException}异常抛出
 	* @param keyword 搜素关键字
 	* @param local true 本地搜索,false 远程搜索
 	* @return
	* @throws android.os.RemoteException
 	*/ 
    List<RegionInfo> searchRegionInfo(String keyword,boolean local);
   
    /**
	 * 获取当前会话session,暂不支持
	 * 
	 * @return
	 * @throws android.os.RemoteException
	 */ 
    String getSessionId();
    
	/**
	 * 打开登录界面输入用户名，密码登录(受限),暂不支持
	 * 
	 * @throws android.os.RemoteException
	 */        
    void showLogin();
    
	/**
	 * 异步后台登录认证(受限),暂不支持
	 * @param userName 用户名
	 * @param password 密码
	 * @param info 返回登录认证信息
	 * @throws android.os.RemoteException
	 */
	void login(String userName,String password,CallBack info);
    
	/**
	 * 后台退出登录(受限),暂不支持
	 * 
	 * @return
	 * @throws android.os.RemoteException
	 */    
    boolean logout();
    
	/**
	 * 后台注册，输入信息注册(受限),暂不支持
	 * 
	 * @throws android.os.RemoteException
	 */
	void register(String userName, String password,CallBack info);
	     
 	/**
	 * 获取此设备在服务器端的设备id,暂不支持
	 * 
	 * @return
	 * @throws android.os.RemoteException
	 */   
    String getDeviceId();
    
	/**
	 * 获取广告信息（注：所有广告都为web方式）,暂不支持
	 * 
	 * @return
	 * @throws android.os.RemoteException
	 */
    List<AdvInfo> getAdvs();
    
	/**
	 * 获取服务的 基础地址（TMS/OMS/BMS）,暂不支持
	 * @param userName ,用户名；null则使用当前登录用户作为参数，当前登录用户为null 则返回null
	 * @return
	 * @throws android.os.RemoteException
	 */    
    String[] getHomePage(String userName);
    
	/**
	 * 获取软件的更新地址，没有更新则返回null,暂不支持
	 * @deprecated Implement {@link #queryMyUpdate()} instead.
	 * @param packageName
	 * @return
	 * @throws android.os.RemoteException
	 */
    AppInfo queryUpdate(String packageName);
    /**获取软件的自己更新地址,暂不支持
	 * @return
	 * @throws android.os.RemoteException
	 */
    AppInfo queryMyUpdate();

	/**返回2维数组,暂不支持
	 * @return versionName，versionCode
	 * @throws android.os.RemoteException
	 */
	String[] getVersion();
	/**重新获取应用启动时传递过去的输入参数,暂不支持
	 * @return
	 * @throws android.os.RemoteException
	 */
	Bundle getAppInput();
	/**获取第三方应用用户信息,暂不支持
	 * @return
	 * @throws android.os.RemoteException
	 */
	UserInfo getSubUsrInfo();


	/**
	 * 应用行为采集,暂不支持
	 * @param data
	 * @throws android.os.RemoteException
	 */
	void appActionCollect(String data);
	/**
	 * 应用数据采集
	 * @param data
	 * @throws android.os.RemoteException
	 */
	void appDataCollect(String data);
///////////////////////////////////////////////////

	
	/**服务调用,暂不支持
	* @param serviceName 服务名称
	* @param methodName 方法名称
	* @param paras 参数接口
	* @throws android.os.RemoteException
	 */
	    Parameter service(String serviceName,String methodName,in Parameter[] paras);
	/**异步调用服务，由接口CallBack 返回数据,暂不支持
	* @param serviceName 服务名
	* @param methodName 方法名
	 * @param paras 参数
	 * @param callBack 回调数据
	* @throws android.os.RemoteException
	*/
	    void asyncService(String serviceName,String methodName,in Parameter[] paras,CallBack callBack);
	/**查询服务对应的接口是否有效,暂不支持
	 * @param serviceName 服务名
	 * @param methodName 接口名
	 * @return
	 * @throws android.os.RemoteException
	 */
	boolean getInterfaceValid(String serviceName,String methodName);
	/**查询服务是否有效,暂不支持
	 * @param serviceName 服务名
	 * @return
	 * @throws android.os.RemoteException
	 */
	boolean getServiceValid(String serviceName);

    /**
	 * 获取程序背景
	 * 
	 * @return 
	 * @throws android.os.RemoteException
	 */ 
    String getCommonBackgroud();
}  