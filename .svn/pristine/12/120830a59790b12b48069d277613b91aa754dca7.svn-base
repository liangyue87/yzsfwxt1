package com.ljx.hfgsjt.workflow.service;

import com.ljx.hfgsjt.util.exception.OneBaseException;
import com.ljx.hfgsjt.util.service.CrudService;
import com.ljx.hfgsjt.util.utils.StringUtils;
import com.ljx.hfgsjt.workflow.dao.FlowTaskInfoEODao;
import com.ljx.hfgsjt.workflow.entity.FlowTaskInfoEO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程节点信息服务
 */
@Service
public class FlowTaskInfoEOService extends CrudService<FlowTaskInfoEODao, FlowTaskInfoEO, String> {

    @Override
    public FlowTaskInfoEO save(FlowTaskInfoEO entity) {
        if (entity.getBindType() == 2) {
        } else if (entity.getBindType() == 3) {
            if (entity.getBindOffice() != null) {
                entity.setBindOfficeId(entity.getBindOffice().getId());
            }
        } else if (entity.getBindType() == 4) {
            if (entity.getBindUser() != null) {
                entity.setBindUserId(entity.getBindUser().getUserId());
            }
        } else if (entity.getBindType() == 5) {
            if (entity.getBindOffice() != null) {
                entity.setBindOfficeId(entity.getBindOffice().getId());
            }
        }
        return super.save(entity);
    }

    @Override
    public List<FlowTaskInfoEO> save(List<FlowTaskInfoEO> entityList) {
        for (FlowTaskInfoEO taskInfoEO : entityList) {
            this.save(taskInfoEO);
        }
        return entityList;
    }

    public FlowTaskInfoEO getFlowTaskInfoEO(String processKey, String taskKey) {
        return dao.getFlowTaskInfoEO(processKey, taskKey);
    }

    public FlowTaskInfoEO getFlowTaskInfoEOAndValid(String processKey, String taskKey) {
        FlowTaskInfoEO flowTaskInfoEO = dao.getFlowTaskInfoEO(processKey, taskKey);
        validProcessTaskInfo(flowTaskInfoEO);
        return flowTaskInfoEO;
    }

    public List<FlowTaskInfoEO> listFlowTaskInfoEOByProcessKey(String processKey) {
        return dao.listFlowTaskInfoEOByProcessKey(processKey);
    }

    public void deleteByProcessKey(String processKey) {
        dao.deleteByProcessKey(processKey);
    }

    public void deleteByTaskKeyNotExist(String processKey, List<String> taskKeyList) {
        dao.deleteByTaskKeyNotExist(processKey, taskKeyList);
    }

    public static void validProcessTaskInfo(FlowTaskInfoEO taskInfoEO) {
        if (taskInfoEO == null) {
            throw new OneBaseException("流程未配置角色和表单");
        }
        if (taskInfoEO.getBindType() == 2) {
            if (StringUtils.isEmpty(taskInfoEO.getBindRoleId())) {
                throw new OneBaseException("流程[" + taskInfoEO.getProcessKey() + "]的节点[" + taskInfoEO.getTaskName() + "]绑定的角色为空");
            }
        } else if (taskInfoEO.getBindType() == 3) {
            if (StringUtils.isEmpty(taskInfoEO.getBindOfficeId())) {
                throw new OneBaseException("流程[" + taskInfoEO.getProcessKey() + "]的节点[" + taskInfoEO.getTaskName() + "]绑定的机构为空");
            }
        } else if (taskInfoEO.getBindType() == 4) {
            if (StringUtils.isEmpty(taskInfoEO.getBindUserId())) {
                throw new OneBaseException("流程[" + taskInfoEO.getProcessKey() + "]的节点[" + taskInfoEO.getTaskName() + "]绑定的用户为空");
            }
        } else if (taskInfoEO.getBindType() == 5) {
            if (StringUtils.isEmpty(taskInfoEO.getBindOfficeId())) {
                throw new OneBaseException("流程[" + taskInfoEO.getProcessKey() + "]的节点[" + taskInfoEO.getTaskName() + "]绑定的机构为空");
            }
            if (StringUtils.isEmpty(taskInfoEO.getBindRoleId())) {
                throw new OneBaseException("流程[" + taskInfoEO.getProcessKey() + "]的节点[" + taskInfoEO.getTaskName() + "]绑定的角色为空");
            }
        } else if (taskInfoEO.getBindType() == 8) {
            if (StringUtils.isEmpty(taskInfoEO.getBindRoleId())) {
                throw new OneBaseException("流程[" + taskInfoEO.getProcessKey() + "]的节点[" + taskInfoEO.getTaskName() + "]绑定的角色为空");
            }
        } else if (taskInfoEO.getBindType() == 9) {
            if (StringUtils.isEmpty(taskInfoEO.getBindService())) {
                throw new OneBaseException("流程[" + taskInfoEO.getProcessKey() + "]的节点[" + taskInfoEO.getTaskName() + "]绑定的服务为空");
            }
        }
    }

}
