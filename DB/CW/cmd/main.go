package main

import (
	"CW/pkg/httpservice"
	"CW/pkg/sqlservice"
	"context"
	"log"
	"time"
)

func main() {
	sqlSvc := sqlservice.NewSQLService("postgres", "postgrespw",
		"localhost", "55000", "course_work")
	sqlContext, cancelSql := context.WithTimeout(context.Background(), time.Second)
	defer cancelSql()
	err := sqlSvc.Start(sqlContext)
	if err != nil {
		log.Fatal(err)
	}

	httpSvc := httpservice.NewHTTPService(80, "localhost")
	httpSvc.ConnectToDataBase(sqlSvc)
	httpSvc.Start()

	select {
	case err := <-httpSvc.GetErrChan():
		if err != nil {
			log.Fatal(err)
		}
	}
}
