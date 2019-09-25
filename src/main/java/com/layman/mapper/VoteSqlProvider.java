package com.layman.mapper;

import com.layman.pojo.Vote;
import com.layman.pojo.VoteExample.Criteria;
import com.layman.pojo.VoteExample.Criterion;
import com.layman.pojo.VoteExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class VoteSqlProvider {

    public String countByExample(VoteExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("vote");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(VoteExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("vote");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(Vote record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("vote");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=VARCHAR}");
        }
        
        if (record.getNumber() != null) {
            sql.VALUES("number", "#{number,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateId() != null) {
            sql.VALUES("create_id", "#{createId,jdbcType=VARCHAR}");
        }
        
        if (record.getUserCount() != null) {
            sql.VALUES("user_count", "#{userCount,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String selectByExample(VoteExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("number");
        sql.SELECT("create_id");
        sql.SELECT("user_count");
        sql.FROM("vote");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        Vote record = (Vote) parameter.get("record");
        VoteExample example = (VoteExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("vote");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=VARCHAR}");
        }
        
        if (record.getNumber() != null) {
            sql.SET("number = #{record.number,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateId() != null) {
            sql.SET("create_id = #{record.createId,jdbcType=VARCHAR}");
        }
        
        if (record.getUserCount() != null) {
            sql.SET("user_count = #{record.userCount,jdbcType=INTEGER}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("vote");
        
        sql.SET("id = #{record.id,jdbcType=VARCHAR}");
        sql.SET("number = #{record.number,jdbcType=VARCHAR}");
        sql.SET("create_id = #{record.createId,jdbcType=VARCHAR}");
        sql.SET("user_count = #{record.userCount,jdbcType=INTEGER}");
        
        VoteExample example = (VoteExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Vote record) {
        SQL sql = new SQL();
        sql.UPDATE("vote");
        
        if (record.getNumber() != null) {
            sql.SET("number = #{number,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateId() != null) {
            sql.SET("create_id = #{createId,jdbcType=VARCHAR}");
        }
        
        if (record.getUserCount() != null) {
            sql.SET("user_count = #{userCount,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, VoteExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}