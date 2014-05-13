/**
 * Wrapper for calling a global 'calculate(params)' function from Java and then
 * convert the result to a se.joelabs.restjs.Result.
 *
 * @param params a se.joelabs.restjs.Params object
 * @returns a se.joelabs.restjs.Result object
 */
function apply(params) {
  var calcRes = calculate(params);
  var Result = Java.type("se.joelabs.restjs.Result");
  return new Result(calcRes.value);
}