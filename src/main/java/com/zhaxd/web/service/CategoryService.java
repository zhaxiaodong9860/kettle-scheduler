package com.zhaxd.web.service;

import com.zhaxd.core.dto.BootTablePage;
import com.zhaxd.core.mapper.KCategoryDao;
import com.zhaxd.core.model.KCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private KCategoryDao kCategoryDao;
  
    public List<KCategory> getList(Integer uId){
        List<KCategory> resultList = new ArrayList<KCategory>();
        KCategory kCategory = new KCategory();
        kCategory.setDelFlag(1);
        kCategory.setAddUser(uId);
        resultList.addAll(kCategoryDao.template(kCategory));
        return resultList;
    }
    
    public BootTablePage getList(Integer start, Integer size, Integer uId){
        KCategory kCategory = new KCategory();
        kCategory.setDelFlag(1);
        kCategory.setAddUser(uId);
        List<KCategory> kQuartzList = kCategoryDao.template(kCategory, start, size);
        long allCount = kCategoryDao.templateCount(kCategory);
        BootTablePage bootTablePage = new BootTablePage();
        bootTablePage.setRows(kQuartzList);
        bootTablePage.setTotal(allCount);
        return bootTablePage;
    }
 
    public KCategory getQuartz(Integer categoryId){
        return kCategoryDao.single(categoryId);
    }
    
    public void insert(KCategory kCategory, Integer uId){
        kCategory.setAddTime(new Date());
        kCategory.setAddUser(uId);
        kCategory.setEditTime(new Date());
        kCategory.setEditUser(uId);
        kCategory.setDelFlag(1);
        kCategoryDao.insert(kCategory);
    }

    public void delete(Integer categoryId){
        KCategory kCategory = kCategoryDao.unique(categoryId);
        kCategory.setDelFlag(0);
        kCategoryDao.updateById(kCategory);
    }

 
    public void update(KCategory kCategory, Integer uId){
        kCategory.setEditTime(new Date());
        kCategory.setEditUser(uId);
        //只有不为null的字段才参与更新
        kCategoryDao.updateTemplateById(kCategory);
    }

    public boolean IsCategoryExist(Integer categoryId,String categoryName) {
        KCategory template = new KCategory();
        template.setDelFlag(1);
        template.setCategoryName(categoryName);
        KCategory category = kCategoryDao.templateOne(template);
        if (null == category) {
            return false;
        } else if(categoryId!=null&&category.getCategoryId()==categoryId){
            return false;
        }else{
            return true;
        }
    }
}
