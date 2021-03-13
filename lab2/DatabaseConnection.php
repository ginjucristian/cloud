<?php


class DatabaseConnection
{
    private string $user;
    private string $password;
    private string $databaseHost;
    private string $databaseName;

    public function __construct(string $user, string $password, string $databaseHost, string $databaseName) {
        $this->user = $user;
        $this->password = $password;
        $this->databaseHost = $databaseHost;
        $this->databaseName = $databaseName;
    }

    /**
     * @return string
     */
    public function getUser(): string
    {
        return $this->user;
    }

    /**
     * @param string $user
     */
    public function setUser(string $user): void
    {
        $this->user = $user;
    }

    /**
     * @return string
     */
    public function getPassword(): string
    {
        return $this->password;
    }

    /**
     * @param string $password
     */
    public function setPassword(string $password): void
    {
        $this->password = $password;
    }

    /**
     * @return string
     */
    public function getDatabaseHost(): string
    {
        return $this->databaseHost;
    }

    /**
     * @param string $databaseHost
     */
    public function setDatabaseHost(string $databaseHost): void
    {
        $this->databaseHost = $databaseHost;
    }

    /**
     * @return string
     */
    public function getDatabaseName(): string
    {
        return $this->databaseName;
    }

    /**
     * @param string $databaseName
     */
    public function setDatabaseName(string $databaseName): void
    {
        $this->databaseName = $databaseName;
    }

    public function getDatabaseConnection(): PDO {

        $options = [
            PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION,
            PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
            PDO::ATTR_EMULATE_PREPARES   => false,
        ];

        return new PDO( "mysql:host=".$this->databaseHost.";dbname=".$this->databaseName, $this->user, $this->password, $options);
    }

}