package com.ligh.dao;

import com.ligh.entity.VideoAccessTopN;
import com.ligh.utils.MysqlUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 面向接口编程
 */
public class VideoAccessTopNDAO {

    static Map<String,String> course = new HashMap<String, String>();
    static {
        course.put("4000","MySQL优化");
        course.put("4500","Crontab");
        course.put("4600","Swift");
        course.put("14540","SpringData");
        course.put("14704","R语言");
        course.put("14390","机器学习");
        course.put("14322","Redis");
        course.put("14390","SparkSQL");
        course.put("14623","Docker");
    }

    /**
     *  根据课程编号获取课程名称
     * @param id
     * @return
     */
    public String getCourseName(String id){
        return course.get(id);
    }
    /**
     *  查询最受欢迎的课程
     *
     * @param day
     * @return
     */
    public List<VideoAccessTopN> query(String day){
        List<VideoAccessTopN> list = new ArrayList<VideoAccessTopN>();

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            connection = MysqlUtil.getConnection();
            String sql = "select cms_id,times from day_video_access_topn_stat where day = ? order by times desc limit 5";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,day);
            rs = pstmt.executeQuery();

            VideoAccessTopN domain = null;
            while(rs.next()){
                domain = new VideoAccessTopN();
                domain.setName(getCourseName(rs.getString("cms_id")));
                domain.setValue(rs.getLong("times"));

                list.add(domain);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        VideoAccessTopNDAO topNDAO = new VideoAccessTopNDAO();
        List<VideoAccessTopN> list = topNDAO.query("20170511");
        for(VideoAccessTopN list1 : list){
            System.out.println(list1.getName() + "===="+list1.getValue());
        }
    }
}
