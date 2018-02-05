package com.neusoft.sipo.prosearch.message.facade.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.neusoft.neusipo.apis.login.bizservice.ILoginService;
import com.neusoft.neusipo.apis.message.bizservice.IMessagePollingService;
import com.neusoft.neusipo.apis.message.domain.Message;
import com.neusoft.neusipo.apis.message.domain.MessageConfig;
import com.neusoft.neusipo.apis.message.domain.MessageUserInfo;
import com.neusoft.neusipo.components.exception.BizException;
import com.neusoft.neusipo.framework.page.Pagination;
import com.neusoft.neusipo.framework.platform.domain.Code;
import com.neusoft.sipo.prosearch.message.facade.MessagePollingFacade;


public class MessagePollingFacadeImpl  implements  MessagePollingFacade {

	private IMessagePollingService messagePollingBS;
	
	public void setMessagePollingBS(IMessagePollingService messagePollingBS) {
		this.messagePollingBS = messagePollingBS;
	}

	@Override
	public List<Message> queryAllMsgs(Message message, Pagination pagination,
			String userId) throws BizException {
		// TODO Auto-generated method stub
		return messagePollingBS.queryAllMsgs(message,pagination,userId);
	}

     
	public List<com.neusoft.neusipo.apis.message.portable.domain.Message> queryAllMsgs(
			com.neusoft.neusipo.apis.message.portable.domain.Message message,
			Pagination pagination, String userId) throws BizException {
		// TODO Auto-generated method stub
		return messagePollingBS.queryAllMsgs(message, pagination, userId);
	}

	@Override
	public int queryInActiveTotal(String userId) throws BizException {
		// TODO Auto-generated method stub
		return messagePollingBS.queryInActiveTotal(userId);
	}

	@Override
	public Message queryMessageDetail(String messageId, int isRead,
			int boxFlag, String userId) throws BizException {
		// TODO Auto-generated method stub
		return messagePollingBS.queryMessageDetail(messageId, isRead, boxFlag, userId);
	}

	@Override
	public List<Message> queryMessageInActive(MessageConfig msgConfig)
			throws BizException {
		// TODO Auto-generated method stub
		return messagePollingBS.queryMessageInActive(msgConfig);
	}

	@Override
	@Cacheable(key ="#msgConfig.userId", value="initMessageCache")
	public List queryMessageIndex(MessageConfig msgConfig) throws BizException {
		// TODO Auto-generated method stub
		return messagePollingBS.queryMessageIndex(msgConfig);
	}

	@Override
	public List<com.neusoft.neusipo.apis.message.portable.domain.Message> queryMessageIndex(
			com.neusoft.neusipo.apis.message.portable.domain.MessageConfig msgConfig)
			throws BizException {
		// TODO Auto-generated method stub
		return messagePollingBS.queryMessageIndex(msgConfig);
	}

	@Override
	public int queryMsgTotal(Message message, String userId)
			throws BizException {
		// TODO Auto-generated method stub
		return messagePollingBS.queryMsgTotal(message, userId);
	}

	@Override
	public List<Code> queryMsgType(String msgType) throws BizException {
		// TODO Auto-generated method stub
		return messagePollingBS.queryMsgType(msgType);
	}

	@Override
	public int querySendMsgTotal(Message message) throws BizException {
		// TODO Auto-generated method stub
		return messagePollingBS.querySendMsgTotal(message);
	}

	@Override
	public List<Message> querySendMsgs(Message message, Pagination pagination)
			throws BizException {
		// TODO Auto-generated method stub
		return messagePollingBS.querySendMsgs(message, pagination);
	}

	@Override
	public boolean queryUserCanPublish(String userId) throws BizException {
		// TODO Auto-generated method stub
		return messagePollingBS.queryUserCanPublish(userId);
	}





}
