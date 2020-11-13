pingServer();

function pingServer() {
    makeRequest();
    setTimeout(pingServer, 10);
}

function makeRequest() {
    let request = new XMLHttpRequest();
    request.open("Get", "/test-ping");
    let time;
    let responseTime;
    request.onreadystatechange = function () {
        if(request.status === 200) {
            responseTime = Date.now();
            console.log(time - responseTime);
        }
    }

    request.send();
    time = Date.now();
}