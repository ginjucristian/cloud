<?php


class AppRouter
{

    private array $routes;

    /**
     * AppRouter constructor.
     * @param array $routes
     */
    public function __construct()
    {
        $this->routes = [];
    }

    /**
     * @return array
     */
    public function getRoutes(): array
    {
        return $this->routes;
    }

    /**
     * @param array $routes
     */
    public function setRoutes(array $routes): void
    {
        $this->routes = $routes;
    }

    /**
     * @param string $route
     */
    public function insertRoute(string $route): void
    {
        array_push($this->routes, $route);
    }

    public function hasRoute(string $route): bool
    {
        foreach ($this->routes as $possibleRoute) {
            if (strpos($route, $possibleRoute) === 0) {
                return True;
            }
        }

        return False;
    }

    public function getRouteFor(string $route): string
    {

    }


}