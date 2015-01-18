dev:
	boot dev

run: node_modules/node-thrust
	node run-thrust.js


node_modules/%:
	npm install $*
