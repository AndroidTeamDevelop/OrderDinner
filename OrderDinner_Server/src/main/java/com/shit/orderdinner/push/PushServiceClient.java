package com.shit.orderdinner.push;

import org.codehaus.jackson.map.util.JSONPObject;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.AddDevicesToTagRequest;
import com.baidu.yun.push.model.AddDevicesToTagResponse;
import com.baidu.yun.push.model.CreateTagRequest;
import com.baidu.yun.push.model.CreateTagResponse;
import com.baidu.yun.push.model.DeleteDevicesFromTagRequest;
import com.baidu.yun.push.model.DeleteDevicesFromTagResponse;
import com.baidu.yun.push.model.DeleteTagRequest;
import com.baidu.yun.push.model.DeleteTagResponse;
import com.baidu.yun.push.model.PushBatchUniMsgRequest;
import com.baidu.yun.push.model.PushBatchUniMsgResponse;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushMsgToAllResponse;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import com.baidu.yun.push.model.PushMsgToSmartTagRequest;
import com.baidu.yun.push.model.PushMsgToSmartTagResponse;
import com.baidu.yun.push.model.PushMsgToTagRequest;
import com.baidu.yun.push.model.PushMsgToTagResponse;

public class PushServiceClient{

	private static final String API_KEY = "fziB22jDYG9kyt87T6yYNwLB";
	private static final String SECRET_KEY = "70zjhssnCpd2ncDDxTvSXRbmov6CDpWe";
	private static final String PUSH_HOST = "api.push.baidu.com";

	private PushKeyPair pushKeyPair;
	private BaiduPushClient baiduPushClient;
	private static PushServiceClient client = null;

	private PushServiceClient() {
		pushKeyPair = new PushKeyPair(API_KEY, SECRET_KEY);
		baiduPushClient = new BaiduPushClient(pushKeyPair, PUSH_HOST);
		baiduPushClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});		
	}
	
	public static PushServiceClient newClient() {
		if(client == null) {
			client = new PushServiceClient();
		}
		return client;
	}
	
	/**
	 * 创建用户自定义的标签组
	 * @param tagName 标签名
	 * @param deviceType 设备类型（3：Android，4：IOS）
	 * @return CreateTagResponse
	 * @throws PushClientException 
	 * @throws PushServerException 
	 */
	public CreateTagResponse createTag(String tagName, int deviceType) throws PushClientException, PushServerException {
		CreateTagRequest request = new CreateTagRequest();
		request.addTagName(tagName)
		.addDeviceType(deviceType);
		return baiduPushClient.createTag(request);
	}
	
	/**
	 * 删除用户自定义的标签组
	 * @param tagName 标签名
	 * @param deviceType 设备类型（3：Android，4：IOS）
	 * @return DeleteTagResponse
	 * @throws PushClientException 
	 * @throws PushServerException 
	 */
	public DeleteTagResponse deleteTag(String tagName, int deviceType) throws PushClientException, PushServerException {
		DeleteTagRequest request = new DeleteTagRequest();
		request.addTagName(tagName)
		.addDeviceType(deviceType);
		return baiduPushClient.deleteTag(request);
	}
	
	/**
	 * 向标签组中批量添加设备
	 * @param channelIds 设备channelId
	 * @param tagName 标签名
	 * @param deviceType 设备类型（3：Android，4：IOS）
	 * @return AddDevicesToTagResponse
	 * @throws PushServerException 
	 * @throws PushClientException 
	 */
	public AddDevicesToTagResponse addDevicesToTag(String[] channelIds, String tagName, int deviceType) throws PushClientException, PushServerException {
		AddDevicesToTagRequest request = new AddDevicesToTagRequest();
		request.addChannelIds(channelIds)
		.addTagName(tagName)
		.addDeviceType(deviceType);
		return baiduPushClient.addDevicesToTag(request);
	}

	/**
	 * 从标签组中批量删除设备
	 * @param channelIds 设备channelId
	 * @param tagName 标签名
	 * @param deviceType 设备类型（3：Android，4：IOS）
	 * @return DeleteDevicesFromTagResponse
	 * @throws PushServerException 
	 * @throws PushClientException 
	 */
	public DeleteDevicesFromTagResponse deleteDevicesFromTag(String[] channelIds, String tagName, int deviceType) throws PushClientException, PushServerException {
		DeleteDevicesFromTagRequest request = new DeleteDevicesFromTagRequest();
		request.addChannelIds(channelIds)
		.addTagName(tagName)
		.addDeviceType(deviceType);
		return baiduPushClient.deleteDevicesFromTag(request);
	}
	
	/**
	 * 向单个设备推送透传消息
	 * @param msgJson 消息内容
	 * @param channelId 设备channelId
	 * @param deviceType 设备类型（3：Android，4：IOS）
	 * @return PushMsgToSingleDeviceResponse
	 * @throws PushServerException 
	 * @throws PushClientException 
	 */
	public PushMsgToSingleDeviceResponse pushMsgToSingleDevice(JSONPObject msgJson, String channelId, int deviceType) throws PushClientException, PushServerException {
		return pushMsgToSingleDevice(msgJson, channelId, deviceType, 0);
	}
	
	/**
	 * 向单个设备推送消息
	 * @param msgJson 消息内容
	 * @param channelId 设备channelId
	 * @param deviceType 设备类型（3：Android，4：IOS）
	 * @param msgType 消息类型（1：通知，0：透传消息，默认为0，注意IOS只有通知。透传消息格式由用户自定义，通知的JSON格式详见技术文档
	 * @return PushMsgToSingleDeviceResponse
	 * @throws PushServerException 
	 * @throws PushClientException 
	 */
	public PushMsgToSingleDeviceResponse pushMsgToSingleDevice(JSONPObject msgJson, String channelId, int deviceType, int msgType) throws PushClientException, PushServerException {
		PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest();
		request.addChannelId(channelId)
		.addMsgExpires(new Integer(3600)) //message有效时间
		.addDeviceType(deviceType)
		.addMessageType(msgType)
		.addMessage(msgJson.toString());
		return baiduPushClient.pushMsgToSingleDevice(request);
	}
	
	/**
	 * 推送消息给批量设备（批量单播）
	 * @param msgJson 消息内容
	 * @param channelIds 设备channelIds
	 * @param deviceType 设备类型（3：Android，4：IOS）
	 * @return
	 * @throws PushClientException
	 * @throws PushServerException
	 */
	public PushBatchUniMsgResponse pushBatchUniMsg(JSONPObject msgJson, String[] channelIds, int deviceType) throws PushClientException, PushServerException {
		return pushBatchUniMsg(msgJson, channelIds, deviceType, 0);
	}
	
	/**
	 * 推送消息给批量设备（批量单播）
	 * @param msgJson 消息内容
	 * @param channelIds 设备channelIds
	 * @param deviceType 设备类型（3：Android，4：IOS）
	 * @param msgType 消息类型（1：通知，0：透传消息，默认为0，注意IOS只有通知。透传消息格式由用户自定义，通知的JSON格式详见技术文档
	 * @return PushBatchUniMsgResponse
	 * @throws PushServerException 
	 * @throws PushClientException 
	 */
	public PushBatchUniMsgResponse pushBatchUniMsg(JSONPObject msgJson, String[] channelIds, int deviceType, int msgType) throws PushClientException, PushServerException {
		PushBatchUniMsgRequest request = new PushBatchUniMsgRequest();
		request.addChannelIds(channelIds)
		.addMsgExpires(new Integer(3600))
		.addDeviceType(deviceType)
		.addMessageType(msgType)
		.addMessage(msgJson.toString());
		return baiduPushClient.pushBatchUniMsg(request);
	}
	
	/**
	 * 推送透传消息给所有设备，即广播推送
	 * @param msgJson 消息内容
	 * @param deviceType 设备类型（3：Android，4：IOS）
	 * @return PushMsgToAllResponse
	 * @throws PushServerException 
	 * @throws PushClientException 
	 */
	public PushMsgToAllResponse pushMsgToAll(JSONPObject msgJson, int deviceType) throws PushClientException, PushServerException {
		return pushMsgToAll(msgJson, deviceType, 0);
	}
	
	/**
	 * 推送消息给所有设备，即广播推送
	 * @param msgJson 消息内容
	 * @param deviceType 设备类型（3：Android，4：IOS）
	 * @param msgType 消息类型（1：通知，0：透传消息，默认为0，注意IOS只有通知。透传消息格式由用户自定义，通知的JSON格式详见技术文档
	 * @return PushMsgToAllResponse
	 * @throws PushServerException 
	 * @throws PushClientException 
	 */
	public PushMsgToAllResponse pushMsgToAll(JSONPObject msgJson, int deviceType, int msgType) throws PushClientException, PushServerException {
		PushMsgToAllRequest request = new PushMsgToAllRequest();
		request.addMsgExpires(new Integer(3600))
		.addDeviceType(deviceType)
		.addMessageType(msgType)
		.addMessage(msgJson.toString());
		return baiduPushClient.pushMsgToAll(request);
	}
	
	/**
	 * 向绑定到tag中的用户推送透传消息
	 * @param msgJson 消息内容
	 * @param tagName 标签名
	 * @param deviceType 设备类型（3：Android，4：IOS）
	 * @return PushMsgToTagResponse
	 * @throws PushServerException 
	 * @throws PushClientException 
	 */
	public PushMsgToTagResponse pushMsgToTag(JSONPObject msgJson, String tagName, int deviceType) throws PushClientException, PushServerException {
		return pushMsgToTag(msgJson, tagName, deviceType, 0);
	}
	
	/**
	 * 向绑定到tag中的用户推送消息
	 * @param msgJson 消息内容
	 * @param tagName 标签名
	 * @param deviceType 设备类型（3：Android，4：IOS）
	 * @param msgType 消息类型（1：通知，0：透传消息，默认为0，注意IOS只有通知。透传消息格式由用户自定义，通知的JSON格式详见技术文档
	 * @return PushMsgToTagResponse
	 * @throws PushServerException 
	 * @throws PushClientException 
	 */
	public PushMsgToTagResponse pushMsgToTag(JSONPObject msgJson, String tagName, int deviceType, int msgType) throws PushClientException, PushServerException {
		PushMsgToTagRequest request = new PushMsgToTagRequest();
		request.addMsgExpires(new Integer(3600))
		.addTagName(tagName)
		.addDeviceType(deviceType)
		.addMessageType(msgType)
		.addMessage(msgJson.toString());
		return baiduPushClient.pushMsgToTag(request);
	}
	
	/**
	 * 推送消息给指定的标签组
	 * @param msgJson 消息内容
	 * @param tagSelector 标签组（格式1：{"OR":{"tag":["xxxx","xxxx"]}}，格式2：{"OR":[{"tag":"xxxx"},{"tag":"xxxx"}]}）
	 * @param deviceType 设备类型（3：Android，4：IOS）
	 * @return PushMsgToSmartTagResponse
	 * @throws PushServerException 
	 * @throws PushClientException 
	 */
	public PushMsgToSmartTagResponse pushMsgToSmartTag(JSONPObject msgJson, JSONPObject tagSelector, int deviceType) throws PushClientException, PushServerException {
		return pushMsgToSmartTag(msgJson, tagSelector, deviceType, 0);
	}
	
	/**
	 * 推送消息给指定的标签组
	 * @param msgJson 消息内容
	 * @param tagSelector 标签组（格式1：{"OR":{"tag":["xxxx","xxxx"]}}，格式2：{"OR":[{"tag":"xxxx"},{"tag":"xxxx"}]}）
	 * @param deviceType 设备类型（3：Android，4：IOS）
	 * @param msgType 消息类型（1：通知，0：透传消息，默认为0，注意IOS只有通知。透传消息格式由用户自定义，通知的JSON格式详见技术文档
	 * @return PushMsgToSmartTagResponse
	 * @throws PushServerException 
	 * @throws PushClientException 
	 */
	public PushMsgToSmartTagResponse pushMsgToSmartTag(JSONPObject msgJson, JSONPObject tagSelector, int deviceType, int msgType) throws PushClientException, PushServerException {
		PushMsgToSmartTagRequest request = new PushMsgToSmartTagRequest();
		request.addSelector(tagSelector.toString())
		.addMsgExpires(new Integer(3600))
		.addDeviceType(deviceType)
		.addMessageType(msgType)
		.addMessage(msgJson.toString());
		return baiduPushClient.pushMsgToSmartTag(request);
	}
	
}
