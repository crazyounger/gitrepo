/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: E:\\workspace-nanjing\\CertusNetCommon\\src\\com\\certusnet\\icity\\mobile\\remote\\CallBack.aidl
 */
package com.certusnet.icity.mobile.remote;
/**
 * 回调信息返回
 * 
 * @author lig
 * @version 1.0
 * @since 1.0 2012-12-28
 */
public interface CallBack extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.certusnet.icity.mobile.remote.CallBack
{
private static final java.lang.String DESCRIPTOR = "com.certusnet.icity.mobile.remote.CallBack";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.certusnet.icity.mobile.remote.CallBack interface,
 * generating a proxy if needed.
 */
public static com.certusnet.icity.mobile.remote.CallBack asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.certusnet.icity.mobile.remote.CallBack))) {
return ((com.certusnet.icity.mobile.remote.CallBack)iin);
}
return new com.certusnet.icity.mobile.remote.CallBack.Stub.Proxy(obj);
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
case TRANSACTION_onMsg:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
com.certusnet.icity.mobile.remote.Parameter _arg1;
if ((0!=data.readInt())) {
_arg1 = com.certusnet.icity.mobile.remote.Parameter.CREATOR.createFromParcel(data);
}
else {
_arg1 = null;
}
this.onMsg(_arg0, _arg1);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.certusnet.icity.mobile.remote.CallBack
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
@Override public void onMsg(int type, com.certusnet.icity.mobile.remote.Parameter msg) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(type);
if ((msg!=null)) {
_data.writeInt(1);
msg.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onMsg, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_onMsg = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void onMsg(int type, com.certusnet.icity.mobile.remote.Parameter msg) throws android.os.RemoteException;
}
