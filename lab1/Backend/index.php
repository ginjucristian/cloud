<?php

ini_set('display_errors', 'Off');
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: ' . $_SERVER['HTTP_ORIGIN']);
header('Access-Control-Allow-Credentials: true');

require_once ("AppRouter.php");
require_once ("config.php");

$appRouter = new AppRouter;

$appRouter->insertRoute("/api/get/ip");
$appRouter->insertRoute("/api/get/location");
$appRouter->insertRoute("/api/get/weather");
$appRouter->insertRoute("/api/metrics");

if($appRouter->hasRoute($_SERVER["REQUEST_URI"]) == false) {
    echo(json_encode(["error_message" => "Route not found"]));
    die;
}

function logRequest($requestLatency, $request, $response) :void{

    $array = [
        "request" => $request,
        "response" => $response,
        "latency" => $requestLatency
    ];

    $file = fopen("./logs.log", "a");

    $encode =  base64_encode(gzencode(json_encode($array))) . PHP_EOL;

    fwrite($file, $encode);
    fclose($file);

}

function getIpExternalRequest() :string
{
    $requestTimeStart = microtime(true);
    $curl = curl_init();

    curl_setopt_array($curl, array(
        CURLOPT_URL => 'https://api.myip.com',
        CURLOPT_RETURNTRANSFER => true,
        CURLOPT_ENCODING => '',
        CURLOPT_MAXREDIRS => 10,
        CURLOPT_TIMEOUT => 0,
        CURLOPT_FOLLOWLOCATION => true,
        CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
        CURLOPT_CUSTOMREQUEST => 'GET',
    ));

    $response = curl_exec($curl);

    curl_close($curl);

    $requestTimeEnd = microtime(true);

    logRequest($requestTimeEnd - $requestTimeStart, $_SERVER["REQUEST_URI"], $response);

    return $response;

}

function getLocationExternalRequest(string $ip) :string
{
    $requestTimeStart = microtime(true);
    $curl = curl_init();

    curl_setopt_array($curl, array(
        CURLOPT_URL => 'http://ip-api.com/json/'.$ip,
        CURLOPT_RETURNTRANSFER => true,
        CURLOPT_ENCODING => '',
        CURLOPT_MAXREDIRS => 10,
        CURLOPT_TIMEOUT => 0,
        CURLOPT_FOLLOWLOCATION => true,
        CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
        CURLOPT_CUSTOMREQUEST => 'GET',
    ));

    $response = curl_exec($curl);
    curl_close($curl);

    $requestTimeEnd = microtime(true);
    logRequest($requestTimeEnd - $requestTimeStart, $_SERVER["REQUEST_URI"], $response);

    return $response;
}

if(strpos($_SERVER["REQUEST_URI"], "/api/get/ip") === 0)
{
    echo getIpExternalRequest();
    die;
}


if(strpos($_SERVER["REQUEST_URI"], "/api/get/location") === 0){

    $url = $_SERVER["REQUEST_URI"];
    $ip = str_replace("/api/get/location", "", $url);


    echo getLocationExternalRequest($ip);
    die;

}

if(strpos($_SERVER["REQUEST_URI"], "/api/get/weather") === 0 ) {

    $requestTimeStart = microtime(true);

    $externalIpResponse = getIpExternalRequest();
    $externalLocationResponse = getLocationExternalRequest(json_decode($externalIpResponse, true)["ip"]);
    $decodedLocation = json_decode($externalLocationResponse, true)["city"];

    $curl = curl_init();


    curl_setopt_array($curl, array(
        CURLOPT_URL => 'http://api.weatherstack.com/current?access_key='.APIKEY.'&query='.$decodedLocation,
        CURLOPT_RETURNTRANSFER => true,
        CURLOPT_ENCODING => '',
        CURLOPT_MAXREDIRS => 10,
        CURLOPT_TIMEOUT => 0,
        CURLOPT_FOLLOWLOCATION => true,
        CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
        CURLOPT_CUSTOMREQUEST => 'GET',
    ));

    $response = curl_exec($curl);

    curl_close($curl);

    $requestTimeEnd = microtime(true);

    logRequest($requestTimeEnd - $requestTimeStart, $_SERVER["REQUEST_URI"], $response);


    echo $response;
    die;

}

if(strpos($_SERVER["REQUEST_URI"], "/api/metrics") === 0 ){

    $array = [];
    $file = fopen("./logs.log", "r");
    while (($line = fgets($file)) !== false) {
        $decode = json_decode(gzdecode(base64_decode($line)), true);
        array_push($array, $decode);
    }

    echo json_encode($array);
}

