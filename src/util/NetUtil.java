/*********************************************
 * Copyright (c) 2019 LI-RTP.
 * All rights reserved.
 * Created on 2019年6月4日
 *
 * Contributors:
 *     rtp - initial implementation
 *********************************************/

package util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetUtil {

	// 获取本机ip
	public static String getLocalIp() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}
	
	// 本机dns域名
	public static String getLocalDnsName() {
		return ""; 
	}
	
}
