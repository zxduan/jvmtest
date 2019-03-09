package cn.duanzx.fortest.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * 设计一个面板，该面板中有四个运动项目选择框和一个文本区。
 * 当某个选择项目被选中时，在文本区中显示该项目
 * */
public class JCheckBoxTest extends JFrame implements ItemListener {
    JPanel jPanel;
    JCheckBox[] jCheckBoxList;
    JTextArea jTextArea;
    String[] itemList = new String[]{"足球","篮球","排球","乒乓球"};
    public void init(){
        jPanel = new JPanel();
        jCheckBoxList = new JCheckBox[4];
        for(int i = 0;i<4;i++){
            jCheckBoxList[i] = new JCheckBox(itemList[i]);
            jPanel.add(jCheckBoxList[i]);
            jCheckBoxList[i].addItemListener(this);
        }
        jTextArea = new JTextArea(6,20);
        jTextArea.setBackground(Color.cyan);
        jPanel.add(jTextArea);
        add(jPanel);
        setBounds(200,200,400,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args)throws Exception{
        new JCheckBoxTest().init();
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        //当前选择了xxx,
        //一共选择了xxx,xxxx
        StringBuffer stringBuffer =new StringBuffer();
        JCheckBox current = (JCheckBox)e.getItemSelectable();
        if(current.isSelected()){
            stringBuffer.append("当前选择了项目：").append(current.getText()).append("\n").append("一共选择了：");
        }else{
            stringBuffer.append("当前取消选择了项目：").append(current.getText()).append("\n").append("一共选择了：");
        }

        for(int i = 0;i<4;i++){
            JCheckBox item = jCheckBoxList[i];
            if(item.isSelected()){
                stringBuffer.append(item.getText()).append(",");
            }
        }
        jTextArea.setText(stringBuffer.toString().substring(0,stringBuffer.length()-1));
    }
}
