package com.neusoft.sipo.prosearch.message.facade.impl;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;

import com.neusoft.neusipo.apis.message.bizservice.IMessageManageService;
import com.neusoft.neusipo.apis.message.domain.Message;
import com.neusoft.neusipo.apis.message.domain.MessageConfig;
import com.neusoft.neusipo.components.exception.BizException;
import com.neusoft.neusipo.framework.platform.domain.Code;
import com.neusoft.sipo.prosearch.message.facade.MessageManageFacade;

public class MessageManageFacadeImpl  implements MessageManageFacade{
	private IMessageManageService manageMessageBS;
	
	public void setManageMessageBS(IMessageManageService manageMessageBS) {
		this.manageMessageBS = manageMessageBS;
	}

	@Override
	@CacheEvict(value="initMessageCache", allEntries=true)
	public int deleteMsgs(String msgIds, int boxId, String userId)
			throws BizException {
		// TODO Auto-generated method stub
		return manageMessageBS.deleteMsgs(msgIds, boxId, userId);
	}

	@Override
	public Map<String, Object> getAttachmentById(String attachmentId)
			throws BizException {
		// TODO Auto-generated method stub
		return manageMessageBS.getAttachmentById(attachmentId);
	}

	@Override
	public int pulbishMessage(Message message) throws BizException {
		// TODO Auto-generated method stub
		return manageMessageBS.pulbishMessage(message);
	}

	@Override
	public List<Code> queryAllReceiver(int receiverType, String msgType) {
		// TODO Auto-generated method stub
		return manageMessageBS.queryAllReceiver(receiverType, msgType);
	}

	@Override
	public Message queryEditSendMsg(String msgId, String userId) {
		// TODO Auto-generated method stub
		return manageMessageBS.queryEditSendMsg(msgId, userId);
	}

	@Override
	public List<Code> queryMsgReceiver(String msgId) {
		// TODO Auto-generated method stub
		return manageMessageBS.queryMsgReceiver(msgId);
	}

	@Override
	public List<Code> queryMsgType(String receiverType) throws BizException {
		// TODO Auto-generated method stub
		return manageMessageBS.queryMsgType(receiverType);
	}

	@Override
	public MessageConfig readMsgConfig(String userId) throws BizException {
		// TODO Auto-generated method stub
		return manageMessageBS.readMsgConfig(userId);
	}

	@Override
	@CacheEvict(value="initMessageCache", allEntries=true)
	public int saveAndPublishMessage(Message message) throws BizException {
		// TODO Auto-generated method stub
		return manageMessageBS.saveAndPublishMessage(message);
	}

	@Override
	public void saveConfig(MessageConfig msgConfig) throws BizException {
		manageMessageBS.saveConfig(msgConfig);
		
	}

	@Override
	@CacheEvict(value="initMessageCache", allEntries=true)
	public String saveMessage(Message message) {
		// TODO Auto-generated method stub
		return manageMessageBS.saveMessage(message);
	}

	@Override
	public int saveUploadAttachment(String msgId, String[] filesFileName,
			List<byte[]> list) throws BizException {
		// TODO Auto-generated method stub
		return manageMessageBS.saveUploadAttachment(msgId, filesFileName, list);
	}

	@Override
	public int updateMsgState(Message message) throws BizException {
		// TODO Auto-generated method stub
		return manageMessageBS.updateMsgState(message);
	}

}
