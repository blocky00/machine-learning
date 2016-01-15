package ml.alg;

import java.math.BigDecimal;
import java.math.MathContext;

import ml.data.Data;
import ml.data.DataSource;

public class GradientDescend {

  private Hypothesis hypothesis;

  private DataSource source;

  private BigDecimal theta1 = BigDecimal.ZERO;

  private BigDecimal theta2 = BigDecimal.ZERO;

  private BigDecimal learningFactor = BigDecimal.valueOf(0.0000003d);

  public GradientDescend(Hypothesis hypothesis, DataSource source) {
    this.hypothesis = hypothesis;
    this.source = source;
  }

  public void start() {
    for (int i = 0; i < 40; i++) {
      CostMatrix cost = new CostMatrix(hypothesis, source, theta1, theta2);

      System.out.println(new ThetaValue(theta1, theta2) + " with cost " + cost.getTotalCost());

      ThetaValue newValue = getNewThethaValues(cost);

      System.out.println("new values:" + newValue);
      theta1 = newValue.getTheta1();
      theta2 = newValue.getTheta2();
    }
  }

  private ThetaValue getNewThethaValues(CostMatrix costMatrix) {
    BigDecimal theta1New = BigDecimal.ZERO, theta2New = BigDecimal.ZERO;

    Data[] dataList = source.fetch();
    int length = dataList.length;

    for (int i = 0; i < length; i++) {
      Data data = dataList[i];
      BigDecimal cost = costMatrix.getCostForIndex(i);

      theta1New = theta1New.add(cost);
      theta2New = theta2New.add(cost.multiply(data.getVar1()));
    }

    theta1New = divide(theta1New, length).multiply(learningFactor);
    theta2New = divide(theta2New, length).multiply(learningFactor);

    return new ThetaValue(theta1.subtract(theta1New), theta2.subtract(theta2New));
  }

  private static BigDecimal divide(BigDecimal op1, int op2) {
    return op1.divide(BigDecimal.valueOf(op2), MathContext.DECIMAL128);
  }
}
