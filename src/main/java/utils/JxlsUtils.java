package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.jxls.common.Context;
import org.jxls.expression.JexlExpressionEvaluator;
import org.jxls.transform.Transformer;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.util.JxlsHelper;
public class JxlsUtils {
    public JxlsUtils() {
    }

    public static void exportExcel(InputStream is, OutputStream os, Map<String, Object> model) throws IOException {
        Context context = PoiTransformer.createInitialContext();
        if (model != null) {
            Iterator var5 = model.keySet().iterator();

            while(var5.hasNext()) {
                String key = (String)var5.next();
                context.putVar(key, model.get(key));
            }
        }

        JxlsHelper jxlsHelper = JxlsHelper.getInstance();
        Transformer transformer = jxlsHelper.createTransformer(is, os);
        JexlExpressionEvaluator evaluator = (JexlExpressionEvaluator)transformer.getTransformationConfig().getExpressionEvaluator();
        evaluator.getJexlEngine().setSilent(true);
        Map<String, Object> funcs = new HashMap();
        funcs.put("utils", new JxlsUtils());
        evaluator.getJexlEngine().setFunctions(funcs);
        jxlsHelper.setUseFastFormulaProcessor(false).processTemplate(context, transformer);
    }

    public static void exportExcel(File xls, File out, Map<String, Object> model) throws FileNotFoundException, IOException {
        exportExcel((InputStream)(new FileInputStream(xls)), (OutputStream)(new FileOutputStream(out)), model);
    }

    public static void exportExcel(String templatePath, OutputStream os, Map<String, Object> model) throws Exception {
        File template = getTemplate(templatePath);
        if (template != null) {
            exportExcel((InputStream)(new FileInputStream(template)), (OutputStream)os, model);
        } else {
            throw new Exception("Excel 模板未找到。");
        }
    }

    public static File getTemplate(String path) {
        File template = new File(path);
        return template.exists() ? template : null;
    }

    public String dateFmt(Date date, String fmt) {
        if (date == null) {
            return "";
        } else {
            try {
                SimpleDateFormat dateFmt = new SimpleDateFormat(fmt);
                return dateFmt.format(date);
            } catch (Exception var4) {
                var4.printStackTrace();
                return "";
            }
        }
    }

    public Object ifelse(boolean b, Object o1, Object o2) {
        return b ? o1 : o2;
    }
}
