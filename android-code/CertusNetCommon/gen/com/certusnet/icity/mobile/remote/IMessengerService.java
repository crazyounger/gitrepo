/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: E:\\workspace-nanjing\\CertusNetCommon\\src\\com\\certusnet\\icity\\mobile\\remote\\IMessengerService.aidl
 */
package com.certusnet.icity.mobile.remote;
/**
 *提供第三方服务接口，由第三方调用。详细请看提供的具体方法
 * @author lig
 * @version 1.0
 * @since 1.0
 */
public interface IMessengerService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.certusnet.icity.mobile.remote.IMessengerService
{
private static final java.lang.String DESCRIPTOR = "com.certusnet.icity.mobile.remote.IMessengerService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.certusnet.icity.mobile.remote.IMessengerService interface,
 * generating a proxy if needed.
 */
public static com.certusnet.icity.mobile.remote.IMessengerService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.certusnet.icity.mobile.remote.IMessengerService))) {
return ((com.certusnet.icity.mobile.remote.IMessengerService)iin);
}
return new com.certusnet.icity.mobile.remote.IMessengerService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_isLogin:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _result = this.isLogin(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getCurrentUserInfo:
{
data.enforceInterface(DESCRIPTOR);
com.certusnet.icity.mobile.remote.UserInfo _result = this.getCurrentUserInfo();
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_getAllUserInfo:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<com.certusnet.icity.mobile.remote.UserInfo> _result = this.getAllUserInfo();
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_getPassword:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _result = this.getPassword(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getCurrentRegionInfo:
{
data.enforceInterface(DESCRIPTOR);
com.certusnet.icity.mobile.remote.RegionInfo _result = this.getCurrentRegionInfo();
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_getRegionInfoByUserName:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
com.certusnet.icity.mobile.remote.RegionInfo _result = this.getRegionInfoByUserName(_arg0);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_getLocalRegionInfo:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<com.certusnet.icity.mobile.remote.RegionInfo> _result = this.getLocalRegionInfo();
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_searchRegionInfo:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _arg1;
_arg1 = (0!=data.readInt());
java.util.List<com.certusnet.icity.mobile.remote.RegionInfo> _result = this.searchRegionInfo(_arg0, _arg1);
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_getSessionId:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getSessionId();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_showLogin:
{
data.enforceInterface(DESCRIPTOR);
this.showLogin();
reply.writeNoException();
return true;
}
case TRANSACTION_login:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
com.certusnet.icity.mobile.remote.CallBack _arg2;
_arg2 = com.certusnet.icity.mobile.remote.CallBack.Stub.asInterface(data.readStrongBinder());
this.login(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_logout:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.logout();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_register:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
com.certusnet.icity.mobile.remote.CallBack _arg2;
_arg2 = com.certusnet.icity.mobile.remote.CallBack.Stub.asInterface(data.readStrongBinder());
this.register(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_getDeviceId:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getDeviceId();
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getAdvs:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<com.certusnet.icity.mobile.remote.AdvInfo> _result = this.getAdvs();
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_getHomePage:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String[] _result = this.getHomePage(_arg0);
reply.writeNoException();
reply.writeStringArray(_result);
return true;
}
case TRANSACTION_queryUpdate:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
com.certusnet.icity.mobile.remote.AppInfo _result = this.queryUpdate(_arg0);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_queryMyUpdate:
{
data.enforceInterface(DESCRIPTOR);
com.certusnet.icity.mobile.remote.AppInfo _result = this.queryMyUpdate();
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_getVersion:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String[] _result = this.getVersion();
reply.writeNoException();
reply.writeStringArray(_result);
return true;
}
case TRANSACTION_getAppInput:
{
data.enforceInterface(DESCRIPTOR);
android.os.Bundle _result = this.getAppInput();
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_getSubUsrInfo:
{
data.enforceInterface(DESCRIPTOR);
com.certusnet.icity.mobile.remote.UserInfo _result = this.getSubUsrInfo();
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_appActionCollect:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.appActionCollect(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_appDataCollect:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.appDataCollect(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_service:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
com.certusnet.icity.mobile.remote.Parameter[] _arg2;
_arg2 = data.createTypedArray(com.certusnet.icity.mobile.remote.Parameter.CREATOR);
com.certusnet.icity.mobile.remote.Parameter _result = this.service(_arg0, _arg1, _arg2);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_asyncService:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
com.certusnet.icity.mobile.remote.Parameter[] _arg2;
_arg2 = data.createTypedArray(com.certusnet.icity.mobile.remote.Parameter.CREATOR);
com.certusnet.icity.mobile.remote.CallBack _arg3;
_arg3 = com.certusnet.icity.mobile.remote.CallBack.Stub.asInterface(data.readStrongBinder());
this.asyncService(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_getInterfaceValid:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
boolean _result = this.getInterfaceValid(_arg0, _arg1);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getServiceValid:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
boolean _result = this.getServiceValid(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getCommonBackgroud:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _result = this.getCommonBackgroud();
reply.writeNoException();
reply.writeString(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.certusnet.icity.mobile.remote.IMessengerService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
/**
	 * 用户是否登录,暂不支持
	 * 
	 * @param userName ;null 则直接判断当前是否有用户登录
	 * @return 用户已登录返回true，否则返回false
	 * @throws android.os.RemoteException
	 */
@Override public boolean isLogin(java.lang.String userName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(userName);
mRemote.transact(Stub.TRANSACTION_isLogin, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * 获取当前登录用户信息
	 * 
	 * @return 用户已登录则返回当前用户信息，否则返回 null
	 * @throws android.os.RemoteException
	 */
@Override public com.certusnet.icity.mobile.remote.UserInfo getCurrentUserInfo() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.certusnet.icity.mobile.remote.UserInfo _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getCurrentUserInfo, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = com.certusnet.icity.mobile.remote.UserInfo.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * 获取所有用户信息(受限)
	 * 
	 * @return 
	 * @throws android.os.RemoteException
	 */
@Override public java.util.List<com.certusnet.icity.mobile.remote.UserInfo> getAllUserInfo() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<com.certusnet.icity.mobile.remote.UserInfo> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getAllUserInfo, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(com.certusnet.icity.mobile.remote.UserInfo.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * 获取用户的密码（MD5）
	 * 
	 * @param userName
	 * @return
	 * @throws android.os.RemoteException
	 */
@Override public java.lang.String getPassword(java.lang.String userName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(userName);
mRemote.transact(Stub.TRANSACTION_getPassword, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * 获取当前登录用户的区域信息
	 * 
	 * @return 没有登录用户则返回null
	 * @throws android.os.RemoteException
	 */
@Override public com.certusnet.icity.mobile.remote.RegionInfo getCurrentRegionInfo() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.certusnet.icity.mobile.remote.RegionInfo _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getCurrentRegionInfo, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = com.certusnet.icity.mobile.remote.RegionInfo.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * 获取 用户的区域信息 （根据输入的用户名）
	 * 
	 * @param userName
	 * @return
	 * @throws android.os.RemoteException
	 */
@Override public com.certusnet.icity.mobile.remote.RegionInfo getRegionInfoByUserName(java.lang.String userName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.certusnet.icity.mobile.remote.RegionInfo _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(userName);
mRemote.transact(Stub.TRANSACTION_getRegionInfoByUserName, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = com.certusnet.icity.mobile.remote.RegionInfo.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * 获取本地已存在的所有区域信息
	 * 
	 * @return
	 * @throws android.os.RemoteException
	 */
@Override public java.util.List<com.certusnet.icity.mobile.remote.RegionInfo> getLocalRegionInfo() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<com.certusnet.icity.mobile.remote.RegionInfo> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getLocalRegionInfo, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(com.certusnet.icity.mobile.remote.RegionInfo.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**搜素区域信息，,暂不支持
	* 注：当local=false并API level>11时，请不要再UI线程中调用此接口，否则返回值为null；
	* 后台隐藏了{@link android.os.NetworkOnMainThreadException}异常抛出
 	* @param keyword 搜素关键字
 	* @param local true 本地搜索,false 远程搜索
 	* @return
	* @throws android.os.RemoteException
 	*/
@Override public java.util.List<com.certusnet.icity.mobile.remote.RegionInfo> searchRegionInfo(java.lang.String keyword, boolean local) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<com.certusnet.icity.mobile.remote.RegionInfo> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(keyword);
_data.writeInt(((local)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_searchRegionInfo, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(com.certusnet.icity.mobile.remote.RegionInfo.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * 获取当前会话session,暂不支持
	 * 
	 * @return
	 * @throws android.os.RemoteException
	 */
@Override public java.lang.String getSessionId() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getSessionId, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * 打开登录界面输入用户名，密码登录(受限),暂不支持
	 * 
	 * @throws android.os.RemoteException
	 */
@Override public void showLogin() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_showLogin, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 异步后台登录认证(受限),暂不支持
	 * @param userName 用户名
	 * @param password 密码
	 * @param info 返回登录认证信息
	 * @throws android.os.RemoteException
	 */
@Override public void login(java.lang.String userName, java.lang.String password, com.certusnet.icity.mobile.remote.CallBack info) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(userName);
_data.writeString(password);
_data.writeStrongBinder((((info!=null))?(info.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_login, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 后台退出登录(受限),暂不支持
	 * 
	 * @return
	 * @throws android.os.RemoteException
	 */
@Override public boolean logout() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_logout, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * 后台注册，输入信息注册(受限),暂不支持
	 * 
	 * @throws android.os.RemoteException
	 */
@Override public void register(java.lang.String userName, java.lang.String password, com.certusnet.icity.mobile.remote.CallBack info) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(userName);
_data.writeString(password);
_data.writeStrongBinder((((info!=null))?(info.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_register, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 获取此设备在服务器端的设备id,暂不支持
	 * 
	 * @return
	 * @throws android.os.RemoteException
	 */
@Override public java.lang.String getDeviceId() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getDeviceId, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * 获取广告信息（注：所有广告都为web方式）,暂不支持
	 * 
	 * @return
	 * @throws android.os.RemoteException
	 */
@Override public java.util.List<com.certusnet.icity.mobile.remote.AdvInfo> getAdvs() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<com.certusnet.icity.mobile.remote.AdvInfo> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getAdvs, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(com.certusnet.icity.mobile.remote.AdvInfo.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * 获取服务的 基础地址（TMS/OMS/BMS）,暂不支持
	 * @param userName ,用户名；null则使用当前登录用户作为参数，当前登录用户为null 则返回null
	 * @return
	 * @throws android.os.RemoteException
	 */
@Override public java.lang.String[] getHomePage(java.lang.String userName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(userName);
mRemote.transact(Stub.TRANSACTION_getHomePage, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * 获取软件的更新地址，没有更新则返回null,暂不支持
	 * @deprecated Implement {@link #queryMyUpdate()} instead.
	 * @param packageName
	 * @return
	 * @throws android.os.RemoteException
	 */
@Override public com.certusnet.icity.mobile.remote.AppInfo queryUpdate(java.lang.String packageName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.certusnet.icity.mobile.remote.AppInfo _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(packageName);
mRemote.transact(Stub.TRANSACTION_queryUpdate, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = com.certusnet.icity.mobile.remote.AppInfo.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**获取软件的自己更新地址,暂不支持
	 * @return
	 * @throws android.os.RemoteException
	 */
@Override public com.certusnet.icity.mobile.remote.AppInfo queryMyUpdate() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.certusnet.icity.mobile.remote.AppInfo _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_queryMyUpdate, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = com.certusnet.icity.mobile.remote.AppInfo.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**返回2维数组,暂不支持
	 * @return versionName，versionCode
	 * @throws android.os.RemoteException
	 */
@Override public java.lang.String[] getVersion() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getVersion, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**重新获取应用启动时传递过去的输入参数,暂不支持
	 * @return
	 * @throws android.os.RemoteException
	 */
@Override public android.os.Bundle getAppInput() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.Bundle _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getAppInput, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = android.os.Bundle.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**获取第三方应用用户信息,暂不支持
	 * @return
	 * @throws android.os.RemoteException
	 */
@Override public com.certusnet.icity.mobile.remote.UserInfo getSubUsrInfo() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.certusnet.icity.mobile.remote.UserInfo _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getSubUsrInfo, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = com.certusnet.icity.mobile.remote.UserInfo.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * 应用行为采集,暂不支持
	 * @param data
	 * @throws android.os.RemoteException
	 */
@Override public void appActionCollect(java.lang.String data) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(data);
mRemote.transact(Stub.TRANSACTION_appActionCollect, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**
	 * 应用数据采集
	 * @param data
	 * @throws android.os.RemoteException
	 */
@Override public void appDataCollect(java.lang.String data) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(data);
mRemote.transact(Stub.TRANSACTION_appDataCollect, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
///////////////////////////////////////////////////
/**服务调用,暂不支持
	* @param serviceName 服务名称
	* @param methodName 方法名称
	* @param paras 参数接口
	* @throws android.os.RemoteException
	 */
@Override public com.certusnet.icity.mobile.remote.Parameter service(java.lang.String serviceName, java.lang.String methodName, com.certusnet.icity.mobile.remote.Parameter[] paras) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.certusnet.icity.mobile.remote.Parameter _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(serviceName);
_data.writeString(methodName);
_data.writeTypedArray(paras, 0);
mRemote.transact(Stub.TRANSACTION_service, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = com.certusnet.icity.mobile.remote.Parameter.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**异步调用服务，由接口CallBack 返回数据,暂不支持
	* @param serviceName 服务名
	* @param methodName 方法名
	 * @param paras 参数
	 * @param callBack 回调数据
	* @throws android.os.RemoteException
	*/
@Override public void asyncService(java.lang.String serviceName, java.lang.String methodName, com.certusnet.icity.mobile.remote.Parameter[] paras, com.certusnet.icity.mobile.remote.CallBack callBack) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(serviceName);
_data.writeString(methodName);
_data.writeTypedArray(paras, 0);
_data.writeStrongBinder((((callBack!=null))?(callBack.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_asyncService, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
/**查询服务对应的接口是否有效,暂不支持
	 * @param serviceName 服务名
	 * @param methodName 接口名
	 * @return
	 * @throws android.os.RemoteException
	 */
@Override public boolean getInterfaceValid(java.lang.String serviceName, java.lang.String methodName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(serviceName);
_data.writeString(methodName);
mRemote.transact(Stub.TRANSACTION_getInterfaceValid, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**查询服务是否有效,暂不支持
	 * @param serviceName 服务名
	 * @return
	 * @throws android.os.RemoteException
	 */
@Override public boolean getServiceValid(java.lang.String serviceName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(serviceName);
mRemote.transact(Stub.TRANSACTION_getServiceValid, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
/**
	 * 获取程序背景
	 * 
	 * @return 
	 * @throws android.os.RemoteException
	 */
@Override public java.lang.String getCommonBackgroud() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getCommonBackgroud, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_isLogin = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getCurrentUserInfo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_getAllUserInfo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_getPassword = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_getCurrentRegionInfo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_getRegionInfoByUserName = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_getLocalRegionInfo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_searchRegionInfo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_getSessionId = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_showLogin = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_login = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_logout = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_register = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
static final int TRANSACTION_getDeviceId = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
static final int TRANSACTION_getAdvs = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
static final int TRANSACTION_getHomePage = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
static final int TRANSACTION_queryUpdate = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16);
static final int TRANSACTION_queryMyUpdate = (android.os.IBinder.FIRST_CALL_TRANSACTION + 17);
static final int TRANSACTION_getVersion = (android.os.IBinder.FIRST_CALL_TRANSACTION + 18);
static final int TRANSACTION_getAppInput = (android.os.IBinder.FIRST_CALL_TRANSACTION + 19);
static final int TRANSACTION_getSubUsrInfo = (android.os.IBinder.FIRST_CALL_TRANSACTION + 20);
static final int TRANSACTION_appActionCollect = (android.os.IBinder.FIRST_CALL_TRANSACTION + 21);
static final int TRANSACTION_appDataCollect = (android.os.IBinder.FIRST_CALL_TRANSACTION + 22);
static final int TRANSACTION_service = (android.os.IBinder.FIRST_CALL_TRANSACTION + 23);
static final int TRANSACTION_asyncService = (android.os.IBinder.FIRST_CALL_TRANSACTION + 24);
static final int TRANSACTION_getInterfaceValid = (android.os.IBinder.FIRST_CALL_TRANSACTION + 25);
static final int TRANSACTION_getServiceValid = (android.os.IBinder.FIRST_CALL_TRANSACTION + 26);
static final int TRANSACTION_getCommonBackgroud = (android.os.IBinder.FIRST_CALL_TRANSACTION + 27);
}
/**
	 * 用户是否登录,暂不支持
	 * 
	 * @param userName ;null 则直接判断当前是否有用户登录
	 * @return 用户已登录返回true，否则返回false
	 * @throws android.os.RemoteException
	 */
public boolean isLogin(java.lang.String userName) throws android.os.RemoteException;
/**
	 * 获取当前登录用户信息
	 * 
	 * @return 用户已登录则返回当前用户信息，否则返回 null
	 * @throws android.os.RemoteException
	 */
public com.certusnet.icity.mobile.remote.UserInfo getCurrentUserInfo() throws android.os.RemoteException;
/**
	 * 获取所有用户信息(受限)
	 * 
	 * @return 
	 * @throws android.os.RemoteException
	 */
public java.util.List<com.certusnet.icity.mobile.remote.UserInfo> getAllUserInfo() throws android.os.RemoteException;
/**
	 * 获取用户的密码（MD5）
	 * 
	 * @param userName
	 * @return
	 * @throws android.os.RemoteException
	 */
public java.lang.String getPassword(java.lang.String userName) throws android.os.RemoteException;
/**
	 * 获取当前登录用户的区域信息
	 * 
	 * @return 没有登录用户则返回null
	 * @throws android.os.RemoteException
	 */
public com.certusnet.icity.mobile.remote.RegionInfo getCurrentRegionInfo() throws android.os.RemoteException;
/**
	 * 获取 用户的区域信息 （根据输入的用户名）
	 * 
	 * @param userName
	 * @return
	 * @throws android.os.RemoteException
	 */
public com.certusnet.icity.mobile.remote.RegionInfo getRegionInfoByUserName(java.lang.String userName) throws android.os.RemoteException;
/**
	 * 获取本地已存在的所有区域信息
	 * 
	 * @return
	 * @throws android.os.RemoteException
	 */
public java.util.List<com.certusnet.icity.mobile.remote.RegionInfo> getLocalRegionInfo() throws android.os.RemoteException;
/**搜素区域信息，,暂不支持
	* 注：当local=false并API level>11时，请不要再UI线程中调用此接口，否则返回值为null；
	* 后台隐藏了{@link android.os.NetworkOnMainThreadException}异常抛出
 	* @param keyword 搜素关键字
 	* @param local true 本地搜索,false 远程搜索
 	* @return
	* @throws android.os.RemoteException
 	*/
public java.util.List<com.certusnet.icity.mobile.remote.RegionInfo> searchRegionInfo(java.lang.String keyword, boolean local) throws android.os.RemoteException;
/**
	 * 获取当前会话session,暂不支持
	 * 
	 * @return
	 * @throws android.os.RemoteException
	 */
public java.lang.String getSessionId() throws android.os.RemoteException;
/**
	 * 打开登录界面输入用户名，密码登录(受限),暂不支持
	 * 
	 * @throws android.os.RemoteException
	 */
public void showLogin() throws android.os.RemoteException;
/**
	 * 异步后台登录认证(受限),暂不支持
	 * @param userName 用户名
	 * @param password 密码
	 * @param info 返回登录认证信息
	 * @throws android.os.RemoteException
	 */
public void login(java.lang.String userName, java.lang.String password, com.certusnet.icity.mobile.remote.CallBack info) throws android.os.RemoteException;
/**
	 * 后台退出登录(受限),暂不支持
	 * 
	 * @return
	 * @throws android.os.RemoteException
	 */
public boolean logout() throws android.os.RemoteException;
/**
	 * 后台注册，输入信息注册(受限),暂不支持
	 * 
	 * @throws android.os.RemoteException
	 */
public void register(java.lang.String userName, java.lang.String password, com.certusnet.icity.mobile.remote.CallBack info) throws android.os.RemoteException;
/**
	 * 获取此设备在服务器端的设备id,暂不支持
	 * 
	 * @return
	 * @throws android.os.RemoteException
	 */
public java.lang.String getDeviceId() throws android.os.RemoteException;
/**
	 * 获取广告信息（注：所有广告都为web方式）,暂不支持
	 * 
	 * @return
	 * @throws android.os.RemoteException
	 */
public java.util.List<com.certusnet.icity.mobile.remote.AdvInfo> getAdvs() throws android.os.RemoteException;
/**
	 * 获取服务的 基础地址（TMS/OMS/BMS）,暂不支持
	 * @param userName ,用户名；null则使用当前登录用户作为参数，当前登录用户为null 则返回null
	 * @return
	 * @throws android.os.RemoteException
	 */
public java.lang.String[] getHomePage(java.lang.String userName) throws android.os.RemoteException;
/**
	 * 获取软件的更新地址，没有更新则返回null,暂不支持
	 * @deprecated Implement {@link #queryMyUpdate()} instead.
	 * @param packageName
	 * @return
	 * @throws android.os.RemoteException
	 */
public com.certusnet.icity.mobile.remote.AppInfo queryUpdate(java.lang.String packageName) throws android.os.RemoteException;
/**获取软件的自己更新地址,暂不支持
	 * @return
	 * @throws android.os.RemoteException
	 */
public com.certusnet.icity.mobile.remote.AppInfo queryMyUpdate() throws android.os.RemoteException;
/**返回2维数组,暂不支持
	 * @return versionName，versionCode
	 * @throws android.os.RemoteException
	 */
public java.lang.String[] getVersion() throws android.os.RemoteException;
/**重新获取应用启动时传递过去的输入参数,暂不支持
	 * @return
	 * @throws android.os.RemoteException
	 */
public android.os.Bundle getAppInput() throws android.os.RemoteException;
/**获取第三方应用用户信息,暂不支持
	 * @return
	 * @throws android.os.RemoteException
	 */
public com.certusnet.icity.mobile.remote.UserInfo getSubUsrInfo() throws android.os.RemoteException;
/**
	 * 应用行为采集,暂不支持
	 * @param data
	 * @throws android.os.RemoteException
	 */
public void appActionCollect(java.lang.String data) throws android.os.RemoteException;
/**
	 * 应用数据采集
	 * @param data
	 * @throws android.os.RemoteException
	 */
public void appDataCollect(java.lang.String data) throws android.os.RemoteException;
///////////////////////////////////////////////////
/**服务调用,暂不支持
	* @param serviceName 服务名称
	* @param methodName 方法名称
	* @param paras 参数接口
	* @throws android.os.RemoteException
	 */
public com.certusnet.icity.mobile.remote.Parameter service(java.lang.String serviceName, java.lang.String methodName, com.certusnet.icity.mobile.remote.Parameter[] paras) throws android.os.RemoteException;
/**异步调用服务，由接口CallBack 返回数据,暂不支持
	* @param serviceName 服务名
	* @param methodName 方法名
	 * @param paras 参数
	 * @param callBack 回调数据
	* @throws android.os.RemoteException
	*/
public void asyncService(java.lang.String serviceName, java.lang.String methodName, com.certusnet.icity.mobile.remote.Parameter[] paras, com.certusnet.icity.mobile.remote.CallBack callBack) throws android.os.RemoteException;
/**查询服务对应的接口是否有效,暂不支持
	 * @param serviceName 服务名
	 * @param methodName 接口名
	 * @return
	 * @throws android.os.RemoteException
	 */
public boolean getInterfaceValid(java.lang.String serviceName, java.lang.String methodName) throws android.os.RemoteException;
/**查询服务是否有效,暂不支持
	 * @param serviceName 服务名
	 * @return
	 * @throws android.os.RemoteException
	 */
public boolean getServiceValid(java.lang.String serviceName) throws android.os.RemoteException;
/**
	 * 获取程序背景
	 * 
	 * @return 
	 * @throws android.os.RemoteException
	 */
public java.lang.String getCommonBackgroud() throws android.os.RemoteException;
}
