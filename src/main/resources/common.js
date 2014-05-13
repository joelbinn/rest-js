function apply(params) {
  var calcRes = calculate(params);
  var Result = Java.type("se.joelabs.restjs.Result");
  return new Result(calcRes.value);
}