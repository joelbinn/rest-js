/**
 * The calculate(params) function of module1
 * @param params a javascript object mapped from se.joelabs.restjs.Params
 * @returns a javascript object: {value: Integer[]}
 */
function calculate(params) {
  var i, j;
  var nAmount = params.costs[0].amounts.length;
  var result = [];
  for(i = 0; i < params.costs.length; i++) {
    for (j = 0; j < nAmount; j++) {
      result[j] = (-params.costs[i].amounts[j]) + (result[j] || 0);
    }
  }

  for(i = 0; i < params.coFinancing.length; i++) {
    for (j = 0; j < nAmount; j++) {
      result[j] = params.coFinancing[i].amounts[j] + (result[j] || 0);
    }
  }

  return {value: result};
}