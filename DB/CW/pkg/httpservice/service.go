package httpservice

import (
	"context"
	"fmt"
	"net"
	"net/http"
	"time"
)

type HTTPService struct {
	port   int
	host   string
	router *Router
	server *http.Server
}

func NewHTTPService(port int, host string) (srv HTTPService) {
	srv.port = port
	srv.host = host

	srv.server = &http.Server{
		Addr:        net.JoinHostPort(srv.host, fmt.Sprint(srv.port)),
		Handler:     srv.router.mux,
		ReadTimeout: 1 * time.Second,
	}
	return
}

func (s *HTTPService) Start(ctx context.Context) error {
	if s.server == nil || s.router == nil {
		return fmt.Errorf("server or router is not initialized")
	}

	return nil
}
