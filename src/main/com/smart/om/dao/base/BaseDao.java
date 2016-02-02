package com.smart.om.dao.base;

import com.smart.om.util.*;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class BaseDao extends HibernateDaoSupport implements DAO {
    private static final String where = "where";
    private static final String wheres = " where ";
    private static final String ands = " and ";

    // DAO对应PO的类名，包括包名
    private String poName;

    // DAO对应PO的元数据对象
    private Class poClazz;

    // DAO对应PO的对象的基本Hql语句
    private String poHqlQuery;

    // DAO对应PO的对象的计算总行数的Hql语句
    private String poHqlCount;

    /**
     * 取得DAO对应PO的对象的基本Hql语句: select o from poName as o
     */
    protected final String getPoHqlQuery() {
        if (poHqlQuery == null && poName != null) {
            StringBuffer sb = new StringBuffer();
            sb.append("from ");
            sb.append(poName);
            sb.append(" ");
            sb.append("o");
            sb.append(" ");
            poHqlQuery = sb.toString();
        }
        return poHqlQuery;
    }

    /**
     * 取得DAO对应PO的对象的基本Hql语句: select o from poName as o
     */
    protected final String getPoHqlCount() {
        if (poHqlCount == null && poName != null) {
            StringBuffer sb = new StringBuffer();
            sb.append(" select count(*) from ");
            sb.append(poName);
            sb.append(" ");
            sb.append("o");
            sb.append(" ");
            poHqlCount = sb.toString();
        }
        return poHqlCount;
    }

    /**
     * 取得查询条件的HQL，可以在子类中重载，直接返回一个Hql字符串
     */
    protected String getHqlQuery() {
        return this.getPoHqlQuery();
    }

    /**
     * 取得查询条件的HQL，可以在子类中重载，直接返回一个Hql字符串
     */
    protected String getHqlCount() {
        return this.getPoHqlCount();
    }

    /**
     * 取得DAO对应PO的类名，包括包名
     */
    public final String getPoName() {
        if (poName == null && poName.length() <= 0) {
            try {
                throw new Exception("没有正确配置poName属性");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return poName;
    }

    /**
     * 设置DAO对应PO的类名，包括包名
     */
    public final void setPoName(String poName) {
        this.poName = poName;
    }

    /**
     * 取得DAO对应PO的的元数据对象，返回Class类
     */
    protected final Class getPoClazz() {
        if (poClazz == null && poName != null) {
            try {
                poClazz = Class.forName(poName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (poClazz == null) {
            try {
                throw new Exception("无法取得PO类的元数据Class对象，可能是没有正确配置poName属性");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return poClazz;
    }

    /**
     * 从数据库中载入一个PO，如果没找到则返回:Null
     */
    public BasePo find(Integer id) {
        if (id == null) {
            return null;
        }
        BasePo po = null;
        Class clazz = getPoClazz();
        if (clazz != null) {
            HibernateTemplate ht = this.getHibernateTemplate();
            po = (BasePo) ht.get(clazz, id);
            if (po != null) {
                ht.evict(po);
            }
        }
        return po;
    }

    /**
     * 从数据库中载入一个PO，如果没找到则返回:Null
     */
    public BasePo find(String id) {
        if (id == null) {
            return null;
        }
        BasePo po = null;
        Class clazz = getPoClazz();
        if (clazz != null) {
            HibernateTemplate ht = this.getHibernateTemplate();
            po = (BasePo) ht.get(clazz, id);
            if (po != null) {
                ht.evict(po);
            }
        }
        return po;
    }

    /**
     * 从数据库中载入一个PO，如果没找到则返回:Null
     */
    public BasePo find(Long id) {
        if (id == null) {
            return null;
        }
        BasePo po = null;
        Class clazz = getPoClazz();
        if (clazz != null) {
            HibernateTemplate ht = this.getHibernateTemplate();
            po = (BasePo) ht.get(clazz, id);
            if (po != null) {
                ht.evict(po);
            }
        }
        return po;
    }

    /**
     * 使PO对象的修改提交到数据库
     */
    protected void flush() {
        HibernateTemplate ht = this.getHibernateTemplate();
        ht.flush();
    }

    /**
     * 使PO对象与Session脱离，变成脱管状态
     */
    protected void evict(BasePo po) {
        if (po != null) {
            HibernateTemplate ht = this.getHibernateTemplate();
            ht.evict(po);
        }
    }

    /**
     * 使PO对象与Session脱离，变成脱管状态，List<BasePo>
     */
    protected void evict(List poList) {
        if (poList != null) {
            HibernateTemplate ht = this.getHibernateTemplate();
            Object o = null;
            Object ob = null;
            for (int i = 0, size = poList.size(); i < size; i++) {
                o = poList.get(i);
                if (o instanceof BasePo) {
                    BasePo po = (BasePo) poList.get(i);
                    ht.evict(po);
                } else if (o instanceof Object[]) {
                    Object[] obs = (Object[]) o;
                    if (obs != null && obs.length > 0) {
                        for (int j = 0; j < obs.length; j++) {
                            ob = obs[j];
                            if (ob instanceof BasePo) {
                                ht.evict(ob);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 保存PO对象到数据库，并立即flush，并返回保存后PO对象
     */
    public BasePo save(BasePo po) {
        if (po == null) {
            return null;
        }
        HibernateTemplate ht = this.getHibernateTemplate();
        // 如果isNewer为true时则保存数据库,否则更新数据
        if (po.isNewer()) {
            ht.save(po);
            po.setNewer(false);
        } else {
            ht.update(po);
        }
        this.flush();
        this.evict(po);
        return po;
    }

    /**
     * 保存PO对象到数据库，并立即flush，并返回保存后PO对象
     */
    public BasePo saveOrUpdate(BasePo po) {
        if (po == null) {
            return null;
        }
        HibernateTemplate ht = this.getHibernateTemplate();
        // 如果isNewer为true时则保存数据库,否则更新数据
        ht.saveOrUpdate(po);
        this.flush();
        this.evict(po);
        return po;
    }

    /**
     * 保存PO对象到数据库，并立即flush，并返回保存后PO对象，List<BasePo>
     */
    public List save(List poList) {
        if (poList == null) {
            return null;
        }
        HibernateTemplate ht = this.getHibernateTemplate();
        for (int i = 0; i < poList.size(); i++) {
            BasePo po = (BasePo) poList.get(i);
            // 如果isNewer为true时则保存数据库,否则更新数据
            if (po.isNewer()) {
                ht.save(po);
                po.setNewer(false);
            } else {
                ht.update(po);
            }
        }
        this.flush();
        this.evict(poList);
        return poList;
    }

    /**
     * 保存PO对象到数据库，并立即flush，并返回保存后PO对象，List<BasePo>
     */
    public List saveOrUpdate(List poList) {
        if (poList == null) {
            return null;
        }
        HibernateTemplate ht = this.getHibernateTemplate();
        for (int i = 0; i < poList.size(); i++) {
            BasePo po = (BasePo) poList.get(i);
            // 如果isNewer为true时则保存数据库,否则更新数据
            ht.saveOrUpdate(po);
        }
        this.flush();
        this.evict(poList);
        return poList;
    }

    /**
     * 通过主键ID删除PO，返回被删除的PO，delete标志是否执行物理删除，物理删除后返回：Null
     */
    public BasePo delete(Integer id) {
        if (id == null) {
            return null;
        }
        HibernateTemplate ht = this.getHibernateTemplate();
        BasePo po = (BasePo) ht.get(getPoClazz(), id);
        if (po != null) {
            po = this.delete(po);
        }
        return po;
    }

    /**
     * 通过主键ID删除PO，返回被删除的PO，delete标志是否执行物理删除，物理删除后返回：Null
     */
    public BasePo delete(Long id) {
        if (id == null) {
            return null;
        }
        HibernateTemplate ht = this.getHibernateTemplate();
        BasePo po = (BasePo) ht.get(getPoClazz(), id);
        if (po != null) {
            po = this.delete(po);
        }
        return po;
    }

    /**
     * 通过主键ID删除PO，返回被删除的PO，delete标志是否执行物理删除，物理删除后返回：Null
     */
    public BasePo delete(String id) {
        if (id == null) {
            return null;
        }
        HibernateTemplate ht = this.getHibernateTemplate();
        BasePo po = (BasePo) ht.get(getPoClazz(), id);
        if (po != null) {
            po = this.delete(po);
        }
        return po;
    }

    /**
     * 删除指定的PO，返回被删除的PO
     */
    public BasePo delete(BasePo po) {
        if (po == null) {
            return null;
        }
        HibernateTemplate ht = this.getHibernateTemplate();
        ht.delete(po);
        ht.flush();
        po = null;
        return po;
    }

    /**
     * 删除指定的PO，返回被删除的PO，delete标志是否执行物理删除，物理删除后返回：Null，List<BasePo>
     */
    public List delete(List poList) {
        if (poList == null || poList.size() <= 0) {
            return null;
        }
        HibernateTemplate ht = this.getHibernateTemplate();
        ht.deleteAll(poList);
        ht.flush();
        poList = null;
        return poList;
    }

    /**
     * 根据查询条件查询PO，QueryData实现了对查询条件的封装， 一般可在Service层和Action层构造QueryData
     */
    public BasePo find(QueryData query) {
        String hql = null;
        Object[] params = null;
        if (query != null) {
            hql = query.getHql();
            params = query.getParams();
        }
        BasePo po = null;
        String hQuery = getHql(getPoHqlQuery(), hql);
        List poList = this.find(hQuery, null, null, params);
        if (poList != null && poList.size() > 0) {
            po = (BasePo) poList.get(0);
        }
        return po;
    }

    /**
     * 将Select子句和Where子句拼接成完整的HQL语句，暂不支持其他子句
     */
    protected final String getHql(String hqlSelect, String hqlWhere) {
        if (hqlSelect == null || hqlSelect.length() <= 0) {
            return null;
        }
        if (hqlWhere == null || hqlWhere.length() <= 0) {
            return hqlSelect;
        }
        int p = hqlSelect.indexOf(where);
        StringBuffer sb = new StringBuffer();
        sb.append(hqlSelect);
        if (p <= 0) {
            sb.append(wheres);
        } else {
            String hqlSel = hqlSelect.toLowerCase();
            p = hqlSel.indexOf(where);
            if (p <= 0) {
                sb.append(wheres);
            } else {
                sb.append(ands);
            }
        }
        sb.append(hqlWhere);
        String hql = null;
        hql = sb.toString();
        return hql;
    }

    /**
     * 根据查询条件和分页信息查询PO，QueryData实现了对查询条件的封装，
     * 一般在Service层和Action层构造QueryData，PageData封装了分页 信息，执行查询后可返回总页数和总行数。
     * 如果在执行分页查询并显示数据时，需要在多次客户端请求之间保存查询 条件和分页信息，一般是保存在HttpSession中，也可以在服务器和浏览
     * 器之间来回传递。
     */
    public List find(QueryData query, PageData page) {
        String hql = null;
        Object[] params = null;
        if (query != null) {
            hql = query.getHql();
            params = query.getParams();
        }
        String hQuery = getHql(getHqlQuery(), hql);
        String hCount = getHql(getHqlCount(), hql);
        return this.find(hQuery, hCount, page, params);
    }

    /**
     * 根据查询HQL和分页HQL查询PO，HQL中如果包含参数，请使用"?"占位符，
     * 而实际的参数放入Object[]参数中，PageData封装了分页信息，执行查询 后可返回总页数和总行数。
     * 可在实现DAO类时，最好不要调用该函数执行特定的查询操作，除非你愿意构造
     * 带有占位符的HQL语句和Object[]数组。警告：HQL中是否有占位符是代码质 量监督的重点，最好不要尝试写出效率较低的HQL。
     */
    protected final List find(String hqlQuery, String hqlCount, PageData page, Object[] params) {
        if (hqlQuery == null || hqlQuery.length() <= 0) {
            return null;
        }
        try {
            HibernateTemplate ht = this.getHibernateTemplate();
            List poList = ht.executeFind(new SessionQuery(hqlQuery, hqlCount, page, params));
            if (poList != null && poList.size() > 0) {
                this.evict(poList);
            }
            return poList;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 根据对象作为条件查询值
     */
    public List findByPo(BasePo po) {
        List lst = null;
        try {
            lst = getHibernateTemplate().findByExample(po);
        } catch (RuntimeException re) {
            re.fillInStackTrace();
        }
        return lst;
    }

    /**
     * 根据对象查询值
     *
     * @param clazz
     * @return
     */
    public List getObjects(Class clazz) {
        List lst = null;
        try {
            String queryString = "from " + clazz.getName();
            lst = getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            re.fillInStackTrace();
        }
        return lst;
    }

    /**
     * 分页查询，hql：查询HQL语句；hqlCount查询总记录HQL语句；page当前是第几页；pageSize：每页显示多少条记录
     */
    public Object[] queryRowByHql(String hql, String hqlCount, Object[] params, int page, int pageSize) {
        PageData pages = new PageData();
        pages.setCurrentPage(page);
        pages.setPageSize(pageSize);
        pages.setRowCount(page + 1);
        List countList = this.find(hqlCount, null, null, params);
        Long longCount = (Long) countList.get(0);
        return new Object[]{this.find(hql, hqlCount, pages, params), longCount.intValue()};
    }

    /**
     * 分页查询，hql：查询HQL语句；hqlCount查询总记录HQL语句；page当前是第几页；pageSize：每页显示多少条记录
     */
    public Object[] queryRowByHqlGroup(String hql, String hqlCount, Object[] params, int page, int pageSize) {
        PageData pages = new PageData();
        pages.setCurrentPage(page);
        pages.setPageSize(pageSize);
        pages.setRowCount(page + 1);
        List countList = this.find(hqlCount, null, null, params);
        int longCount = countList.size();
        return new Object[]{this.find(hql, hqlCount, pages, params), longCount};
    }

    /**
     * 分页查询，hql：查询HQL语句；hqlCount查询总记录HQL语句；page当前是第几页；pageSize：每页显示多少条记录
     */
    public PageModel queryPageByHql(String hql, String hqlCount, Object[] params, int page, int pageSize) {
        PageModel pageModel = new PageModel();// 返回的分页模型
        PageData pages = new PageData();
        pages.setCurrentPage(page);
        pages.setPageSize(pageSize);
        List list = this.find(hql, hqlCount, pages, params);
        pageModel.setDatas(list);// 获得当页集合
        List countList = this.find(hqlCount, null, null, params);
        Long longCount = 0l;
        if (countList != null && countList.size() > 0) {
            longCount = (Long) countList.get(0);
        }
        pageModel.setTotal(longCount.intValue());// 获得总的记录数
        return pageModel;
    }

    /**
     * 分页查询，hql：查询HQL语句；hqlCount查询总记录HQL语句；page当前是第几页；pageSize：每页显示多少条记录
     */
    public PageModel queryPageByHqlMySQl(String hql, String hqlCount, Object[] params, int page, int pageSize) {
        PageModel pageModel = new PageModel();// 返回的分页模型
        PageData pages = new PageData();
        pages.setCurrentPage(page);
        pages.setPageSize(pageSize);
//		pages.setRowCount(page+1);
        List list = this.find(hql, hqlCount, pages, params);
        pageModel.setDatas(list);// 获得当页集合
        List countList = this.find(hqlCount, null, null, params);
        Long longCount = 0l;
        if (countList != null && countList.size() > 0) {
            longCount = (Long) countList.get(0);
        }
        pageModel.setTotal(longCount.intValue());// 获得总的记录数
        return pageModel;
    }

    /**
     * 分页查询
     *
     * @param hql
     * @param hqlCount
     * @param pageData
     * @return
     */
    public DTablePageModel getDTablePageModel(String hql, String hqlCount, PageData pageData) {
        PageModel pm = this.queryPageByHqlMySQl(hql.toString(), hqlCount.toString(), null, pageData.getCurrentPage(), pageData.getPageSize());
        DTablePageModel dtPageModel = new DTablePageModel(pm.getDatas(), pm.getTotal());
        dtPageModel.setsEcho(pageData.getsEcho());
        return dtPageModel;
    }

    // 根据HQL查询记录数
    public int queryCountByHql(String hql) {
        List countList = this.find(hql, null, null, null);
        Long longCount = 0l;
        if (countList != null && countList.size() > 0) {
            longCount = (Long) countList.get(0);
        }
        return longCount.intValue();
    }

    /**
     * 根据参数查询HQL语句
     **/
    public List find(String hqlQuery, Object[] params) {
        if (hqlQuery == null || hqlQuery.length() <= 0) {
            return null;
        }
        try {
            HibernateTemplate ht = this.getHibernateTemplate();
            List poList = ht.executeFind(new SessionQuery(hqlQuery, params));
            if (poList != null && poList.size() > 0) {
                this.evict(poList);
            }
            return poList;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 使用HQL做修改和删除操作
     **/
    public int updateHql(String hqlQuery, Object[] params) {
        int result = 0;
        try {
            HibernateTemplate ht = this.getHibernateTemplate();
            result = ht.bulkUpdate(hqlQuery, params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * @param persistName 查询的表名
     * @param colName     字段名
     * @param value       字段值
     * @return
     */
    public boolean isExistPersist(String persistName, String colName, Integer value) {
        StringBuffer hql = new StringBuffer();
        hql.append("select * from" + persistName + "where " + colName + "=" + value);
        List list = this.find(hql.toString(), null);
        return list.size() > 0;

    }

}

