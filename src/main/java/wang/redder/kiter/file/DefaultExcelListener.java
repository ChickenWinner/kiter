package wang.redder.kiter.file;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析监听器 包含了解析后的数据以及解析前后空可以调用的方法
 *
 *
 * @author Red
 * email: 1318944013@qq.com
 * date: 2019/4/2 13:42
 */
public class DefaultExcelListener extends AnalysisEventListener {

    //自定义用于暂时存储data。
    //可以通过实例获取该值
    private List<Object> datas = new ArrayList<Object>();

    // 可以在该方法中对数据进操作， 或者执行过滤方法
    public void invoke(Object object, AnalysisContext context) {
        // 存储数据
        datas.add(object);
        // 根据自己业务做处理 目前是空实现
        doSomething(object);
    }

    private void doSomething(Object object) {
        //1、入库调用接口
    }
    

    public void doAfterAllAnalysed(AnalysisContext context) {
        // datas.clear();//解析结束销毁不用的资源
    }

    public List<Object> getDatas() {
        return datas;
    }

    public void setDatas(List<Object> datas) {
        this.datas = datas;
    }
}
