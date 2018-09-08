# shadow-clj-fullstack

Example of full-stack Clojure/Script project which uses shadow-cljs for clojure-script compilation.

It allows using any arbitrary NPM dependencies, like `"@blueprintjs/core"` which is 
close to impossible with `lein-cljsbuild`.

## Stack

- `clojure` <3
- `timbre` for logging
- `mount` for state management
- `pedestal` for web server and `geheimtur` for Oauth2 protection
- `hiccup` for HTML generation on server side
- `wrench` for managing configuration via environment variabes
- `garden` for generating CSS with `garden-gnome` to watch changes
- `shadow-cljs` for compiling clojure-script and hot code reload
- `reagent` for rendering hiccup-like forms as React components
- `re-frame` for frontend state-management
- `bidi` & `pushy` for single-page-app navigation

## Development

Copy `dev/config-example.edn` to `dev/config.edn` and fill empty variables.

Install both NPM and Leiningen:

    brew install leiningen node yarn
    
Now you are ready to start the project locally

    yarn install
    lein repl
    > (reset)

This would
 
- start a local web-server on 8080 port
- start shadow-cljs server with hot-reload of your javascript
- start garden-gnome, which will compile your styles and watch for changes 
  (chadow-cljs will pick up those changes on frontend)
  
  
### CSS styles with Garden

Styles are compiled from `src/css/styles.clj` with [Garden](https://github.com/noprompt/garden)


## License

Copyright © 2018 Anton Chebotaev

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
