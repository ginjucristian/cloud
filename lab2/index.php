<?php

//ini_set('display_errors', 'Off');
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: ' . $_SERVER['HTTP_ORIGIN']);
header('Access-Control-Allow-Credentials: true');

require_once ("AppRouter.php");
require_once ("config.php");
require_once ("DatabaseConnection.php");

$appRouter = new AppRouter;
$databaseConnection = new DatabaseConnection("root", "", "localhost", "games_api_database");

$appRouter->insertRoute("/api/games");
$appRouter->insertRoute("/api/games/byID");


if ($appRouter->hasRoute($_SERVER["REQUEST_URI"]) == false) {
    echo(json_encode(["error_message" => "Route not found"]));
    die;
}

if (strpos($_SERVER["REQUEST_URI"], "/api/games") === 0  && $_SERVER["REQUEST_METHOD"] == "POST") {

    $body = file_get_contents('php://input');

    $arr = json_decode($body, true);

    $dbConn = $databaseConnection->getDatabaseConnection();

    $name = $arr["name"];
    $genre = $arr["genre"];
    $hours_played = $arr["hours_played"];
    $rating = $arr["rating"];

    $dbConn->prepare("INSERT INTO games(name, genre, hours_played, rating) VALUES (:name, :genre, :hours_played, :rating)")->execute([
        "name" => $name,
        "genre" => $genre,
        "hours_played" => $hours_played,
        "rating" => $rating
    ]);

    echo(json_encode([
        "id" => $dbConn->lastInsertId(),
        "name" => $name,
        "genre" => $genre,
        "hours_played" => $hours_played,
        "rating" => $rating
    ]));

    die;
}

if (strpos($_SERVER["REQUEST_URI"], "/api/games/byID") === 0  && $_SERVER["REQUEST_METHOD"] == "GET") {

    $dbConn = $databaseConnection->getDatabaseConnection();

    $statement = $dbConn->prepare("SELECT * FROM games WHERE id=:id");
    $statement->execute(["id" => $_GET["id"]]);
    $rows=$statement->fetchAll();

    $result = [];

    foreach ($rows as $row) {
        array_push($result, $row);
    }

    echo json_encode($result);

    die;
}

if (strpos($_SERVER["REQUEST_URI"], "/api/games") === 0  && $_SERVER["REQUEST_METHOD"] == "GET") {

    $dbConn = $databaseConnection->getDatabaseConnection();
    $rows = $dbConn->query("SELECT * FROM games");
    $result = [];

    foreach ($rows as $row) {
        array_push($result, $row);
    }

    echo json_encode($result);

    die;
}

if (strpos($_SERVER["REQUEST_URI"], "/api/games/byID") === 0  && $_SERVER["REQUEST_METHOD"] == "PUT") {

    $body = file_get_contents('php://input');

    $arr = json_decode($body, true);

    $dbConn = $databaseConnection->getDatabaseConnection();

    $name = $arr["name"];
    $genre = $arr["genre"];
    $hours_played = $arr["hours_played"];
    $rating = $arr["rating"];

    $dbConn->prepare("UPDATE games SET name=:name, genre=:genre, hours_played=:hours_played, rating=:rating WHERE id=:id")->execute([
        "id" => $_GET["id"],
        "name" => $name,
        "genre" => $genre,
        "hours_played" => $hours_played,
        "rating" => $rating
    ]);

    echo(json_encode([
        "id" => $_GET["id"],
        "name" => $name,
        "genre" => $genre,
        "hours_played" => $hours_played,
        "rating" => $rating
    ]));

    die;
}

if (strpos($_SERVER["REQUEST_URI"], "/api/games/byID") === 0  && $_SERVER["REQUEST_METHOD"] == "DELETE") {

    $dbConn = $databaseConnection->getDatabaseConnection();

    $statement = $dbConn->prepare("DELETE FROM games WHERE id=:id");
    $statement->execute(["id" => $_GET["id"]]);
    $rows=$statement->fetchAll();

    echo(json_encode([
        "success" => true
    ]));

    die;
}