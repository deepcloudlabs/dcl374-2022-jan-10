function fun(x, y, z) {
    for (let arg of arguments)
        console.error(arg)
    if(arguments.length !== 3){
        throw "You must provide exactly three parameters";
    }
    return x * y + z;
}

console.log(fun(1,2,3,4,5));
console.log(fun(1,2,3,4));
console.log(fun(1,2,3));
console.log(fun(1,2));
console.log(fun(1));
console.log(fun());
