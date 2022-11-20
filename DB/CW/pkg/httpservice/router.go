package httpservice

import (
	"github.com/gorilla/mux"
	"net/http"
)

type Router struct {
	mux *mux.Router
}

func (r *Router) addHandlers() {
	r.mux.HandleFunc("/", r.indexHandler)
	r.mux.HandleFunc("/table/{tablename}", r.tableHandler)
}

func (r *Router) indexHandler(writer http.ResponseWriter, request *http.Request) {

}

func (r *Router) tableHandler(writer http.ResponseWriter, request *http.Request) {
	_ = mux.Vars(request)["tablename"]

}
