package main

import (
	"CW/pkg/httpservice"
	"CW/pkg/sqlservice"
	"context"
	"log"
	"time"
)

func main() {
	logger := log.Default()
	logger.SetFlags(log.Ldate | log.Lmicroseconds)

	sqlSvc := sqlservice.NewSQLService("postgres", "",
		"192.168.142.4", "5432", "tech_support_db", logger)
	sqlContext, cancelSql := context.WithTimeout(context.Background(), time.Second)
	defer cancelSql()
	err := sqlSvc.Start(sqlContext)
	if err != nil {
		log.Fatal(err)
	}

	httpSvc := httpservice.NewHTTPService(80, "127.0.0.1", logger)
	httpSvc.ConnectToDataBase(sqlSvc)
	httpSvc.Start()

	select {
	case err := <-httpSvc.GetErrChan():
		if err != nil {
			log.Fatal(err)
		}
	}
}
