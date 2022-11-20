package main

import (
	"CW/pkg/httpservice"
	"CW/pkg/sqlservice"
	"context"
	"log"
	"time"
)

func main() {
	serviceCtx, cancelFunc := context.WithCancel(context.Background())
	defer cancelFunc()

	sqlSvc := sqlservice.NewSQLService("postgres", "postgrespw",
		"localhost", "55000", "course_work")
	sqlContext, cancelSql := context.WithTimeout(serviceCtx, time.Second)
	defer cancelSql()
	err := sqlSvc.Connect(sqlContext)
	if err != nil {
		log.Fatal(err)
	}

	httpSvc := httpservice.NewHTTPService(80, "http://localhost")
	err = httpSvc.Start(serviceCtx)
	if err != nil {
		log.Fatal(err)
	}

}
