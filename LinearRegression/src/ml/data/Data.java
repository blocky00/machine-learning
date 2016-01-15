package ml.data;

import java.math.BigDecimal;

public class Data {

  private BigDecimal var1;

  private BigDecimal var2;

  public Data(String var1, String var2) {
    this(new BigDecimal(var1), new BigDecimal(var2));
  }

  public Data(BigDecimal var1, BigDecimal var2) {
    this.var1 = var1;
    this.var2 = var2;
  }

  public BigDecimal getVar1() {
    return var1;
  }

  public BigDecimal getVar2() {
    return var2;
  }
}