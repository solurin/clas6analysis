import org.jlab.groot.data.*;
import org.jlab.groot.graphics.*;
import org.jlab.groot.math.*;
import org.jlab.groot.ui.*;
import org.jlab.clas.physics.*;








JFrame frame = new JFrame("Basic GROOT Demo");
EmbeddedCanvas canvas = new EmbeddedCanvas();
frame.setSize(800,500);
H2F histogram2d = FunctionFactory.randomGausian2D(40, 0.4, 7.6, 800000, 3.3, 0.8);
histogram2d.setTitleX("Randomly Generated Function");
histogram2d.setTitleY("Randomly Generated Function");		
canvas.getPad(0).setTitle("Histogram2D Demo");
canvas.draw(histogram2d);
canvas.setFont("HanziPen TC");  
canvas.setTitleSize(32);
canvas.setAxisTitleSize(24);
canvas.setAxisLabelSize(18);
canvas.setStatBoxFontSize(18);
frame.add(canvas);
frame.setLocationRelativeTo(null);
frame.setVisible(true);

