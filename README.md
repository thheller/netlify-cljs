# netlify-cljs

Test project using `shadow-cljs` to deploy using `netlify` automatic deploys via github.

Currently deployed to:
https://confident-goldwasser-1f7161.netlify.com/

During development, start shadow-cljs in server mode and either use http://localhost:9630 to control builds or use additional terminals.

```
npm install
npx shadow-cljs server
```

Backend only really supports compile/release. watch is probably not gonna be all that reliable, didn't look further into it yet. The netlify-lambda util probably needs to be restarted when the lambda code is recompiled.
```
# different terminal
npx shadow-cljs compile lambda
npx netlify-lambda serve functions
```

Frontend can be development like normal. The built-in dev http server will proxy the function request to the running `netlify-lambda` process.

```
# different terminal
npx shadow-cljs watch frontend
open http://localhost:6500
```

## Notes

Everything builds fine on its own but `netlify` currently only caches `.m2` dependencies if a `project.clj` or `build.boot` file is found. We can work without cache but it makes the builds a bit slower if all the deps downloaded each time.

There doesn't seem to be a way to tell `netlify` to cache `.shadow-cljs/builds` so incremental builds are not supported and everything will always recompile from scratch. A typical deploy on this very simple project takes about 2 minutes.