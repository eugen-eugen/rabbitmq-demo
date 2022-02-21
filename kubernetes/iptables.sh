for p in 30672 31672  6443 ; do
sudo iptables -t nat -A DOCKER -p tcp --dport $p -j DNAT --to-destination 172.17.0.2:$p
sudo iptables -A DOCKER -j ACCEPT -p tcp --destination 172.17.0.2 --dport $p 
sudo iptables -t nat -A POSTROUTING -j MASQUERADE -p tcp --source 172.17.0.2 --destination 172.17.0.2 --dport $p 
done
