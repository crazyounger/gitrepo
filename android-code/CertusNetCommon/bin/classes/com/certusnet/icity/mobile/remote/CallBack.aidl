package com.certusnet.icity.mobile.remote;
import com.certusnet.icity.mobile.remote.Parameter;
/**
 * 回调信息返回
 * 
 * @author lig
 * @version 1.0
 * @since 1.0 2012-12-28
 */
interface CallBack{
	void onMsg(int type,in Parameter msg);
}
