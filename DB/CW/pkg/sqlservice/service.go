package sqlservice

import (
	"context"
	"database/sql"
	"fmt"
	_ "github.com/lib/pq"
)

type SQLService struct {
	Username string
	Password string
	Host     string
	Port     string
	DbName   string
	db       *sql.DB
}

func NewSQLService(username, password, host, port, dbName string) *SQLService {
	return &SQLService{
		Username: username,
		Password: password,
		Host:     host,
		Port:     port,
		DbName:   dbName,
	}
}

func (s *SQLService) Connect(ctx context.Context) (err error) {
	connStr := fmt.Sprintf("user=%s password=%s host=%s port=%s database=%s sslmode=disable",
		s.Username, s.Password, s.Host, s.Port, s.DbName)
	s.db, err = sql.Open("postgres", connStr)
	if err != nil {
		return fmt.Errorf("open SQL connection: %v", err)
	}

	err = s.db.PingContext(ctx)
	if err != nil {
		return fmt.Errorf("ping database: %v", err)
	}

	s.db.Query("tabl")
	return nil
}
